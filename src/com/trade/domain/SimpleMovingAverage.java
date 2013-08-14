package com.trade.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


public class SimpleMovingAverage extends Model
{
  private Integer averageCount;

  public Integer getAverageCount()
  {
    return averageCount;
  }

  public void setAverageCount( Integer averageCount )
  {
    this.averageCount = averageCount;
  }
}
