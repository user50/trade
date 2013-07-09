package com.trade.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="dynamic gamma-poisson")
public class DynamicGammaPoisson extends Model
{
  private Double alpha;

  private Integer sign;

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
}
