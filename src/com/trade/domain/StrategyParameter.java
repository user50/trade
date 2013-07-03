package com.trade.domain;

import javax.persistence.*;

@Entity
public class StrategyParameter
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  String name;
  Double value;

  @ManyToOne
  Strategy strategy;

  public Integer getId()
  {
    return id;
  }

  public void setId( Integer id )
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public Double getValue()
  {
    return value;
  }

  public void setValue( Double value )
  {
    this.value = value;
  }

  public Strategy getStrategy()
  {
    return strategy;
  }

  public void setStrategy( Strategy strategy )
  {
    this.strategy = strategy;
  }
}
