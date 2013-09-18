package com.trade.algorithms;

import com.avaje.ebean.Ebean;
import com.trade.domain.*;

public class DynamicGammaPoisson
{
  public static void calculateIndicator(FinancialInstrument instrument, Model model)
  {
    Values values = new Values( getStartMean( model ), getStartDispersion( model ) );
    for( Bar bar : Bar.get( instrument.getId() ) )
    {
      int totalCount = bar.getVolume() * instrument.getVolumeMultiplier();
      int delta = (int) ((bar.getClose() - bar.getOpen()) * Math.pow( 10.0, instrument.getPips() ));
      int positiveCount = (totalCount - Math.abs( delta ) )/2 + delta;

      values = update( values, totalCount, positiveCount, getAlpha( model ) );

      model.add( new Indicator( "mean", values.mean, model, bar  ) );
      model.add( new Indicator( "dispersion", values.dispersion, model, bar  ) );
    }

    Ebean.update( model );
  }

  private static Values update(Values values, int totalCount, int positiveCount, double alpha)
  {
    double gamma = values.mean/ values.dispersion;
    double improvedGamma = gamma + totalCount;

    double improvedMu = (values.mean * gamma + positiveCount)/improvedGamma;
    double improvedDispersion = values.mean/improvedGamma;

    return new Values(improvedMu, improvedDispersion + alpha*(improvedMu * improvedMu + improvedDispersion ) );
  }

  private static class Values
  {
    public double mean;
    public double dispersion;

    private Values( double mean, double dispersion )
    {
      this.mean = mean;
      this.dispersion = dispersion;
    }
  }

  private static double getStartMean(Model model)
  {
    return getDoubleParameter( model, "startMean" );
  }

  private static double getStartDispersion(Model model)
  {
    return getDoubleParameter( model, "startDispersion" );
  }

  private static double getAlpha(Model model)
  {
    return getDoubleParameter( model, "alpha" );
  }

  private static double getDoubleParameter(Model model, String name)
  {
    for( Parameter parameter : model.getParameters() )
    {
      if( parameter.getName().equals( name ) )
        return Double.parseDouble( parameter.getValue() );
    }

    throw new IllegalStateException("Model instance does not contain property '"+name+"'");
  }

  public static Model createDynamicGammaPoissonModel(double startMean, double startDispersion, double alpha)
  {
    Model model = new Model();
    model.setType( ModelType.dynamicGammaPoisson );
    model.add( new Parameter( "startMean", startMean+"", "double" ) );
    model.add( new Parameter( "startDispersion", startDispersion+"", "double" ) );
    model.add( new Parameter( "alpha", alpha+"", "double" ) );

    return model;
  }
}
