package com.trade.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BarModelPK implements Serializable
{
  public Integer barId;

  public Integer modelId;

  @Override
  public boolean equals( Object o )
  {
    if( this == o )
    {
      return true;
    }
    if( o == null || getClass() != o.getClass() )
    {
      return false;
    }

    BarModelPK that = (BarModelPK) o;

    if( !barId.equals( that.barId ) )
    {
      return false;
    }
    if( !modelId.equals( that.modelId ) )
    {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode()
  {
    int result = barId.hashCode();
    result = 31 * result + modelId.hashCode();
    return result;
  }
}
