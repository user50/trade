package com.trade.domain;

import com.avaje.ebean.Ebean;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value="dynamic gamma-poisson")
public class DynamicGammaPoisson extends Model
{
  private Double alpha;

  private Integer sign;

  private Double startMean;

  private Double startDispersion;

  @OneToMany(cascade = CascadeType.ALL)
  private List<DynamicGammaPoissonIndicator> indicators = new ArrayList<DynamicGammaPoissonIndicator>(  );

  public Double getAlpha()
  {
    return alpha;
  }

  public void setAlpha( Double alpha )
  {
    this.alpha = alpha;
  }

  public Integer getSign()
  {
    return sign;
  }

  public void setSign( Integer sign )
  {
    this.sign = sign;
  }

  public List<DynamicGammaPoissonIndicator> getIndicators()
  {
    return indicators;
  }

  public void setIndicators( List<DynamicGammaPoissonIndicator> indicators )
  {
    this.indicators = indicators;
  }

  public Double getStartMean()
  {
    return startMean;
  }

  public void setStartMean( Double startMean )
  {
    this.startMean = startMean;
  }

  public Double getStartDispersion()
  {
    return startDispersion;
  }

  public void setStartDispersion( Double startDispersion )
  {
    this.startDispersion = startDispersion;
  }

  public void calculateIndicator(FinancialInstrument instrument)
  {
    DynamicGammaPoissonIndicator parameters = new DynamicGammaPoissonIndicator( startMean, startDispersion );
    for( Bar bar : instrument.getBars() )
    {
      int totalCount = bar.getVolume();
      int delta = (int) ((bar.close - bar.open) * Math.pow( 10.0, instrument.getPips() ));
      int positiveCount = (totalCount - Math.abs( delta ) ) + delta;

      DynamicGammaPoissonIndicator nextParameters = update( parameters, totalCount, positiveCount, alpha );
      indicators.add( nextParameters );

    }

    Ebean.update( this );
  }

  public static DynamicGammaPoissonIndicator update(DynamicGammaPoissonIndicator parameters, int totalCount, int positiveCount, double alpha)
  {
    double gamma = parameters.mean/ parameters.dispersion;
    double improvedGamma = gamma + totalCount;

    double improvedMu = (parameters.mean * gamma + positiveCount)/improvedGamma;
    double improvedDispersion = parameters.mean/improvedGamma;

    return new DynamicGammaPoissonIndicator(improvedMu, improvedDispersion + alpha*(improvedMu * improvedMu + improvedDispersion ) );
  }

}
