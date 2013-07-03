package com.trade.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StrategyType
{
  public static enum StrategyTypeName
  {
    dynamicGammaPoisson, sma

  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  StrategyTypeName name;

  @OneToMany(cascade = CascadeType.ALL)
  List<Strategy> strategies = new ArrayList<Strategy>(  );

  public Integer getId()
  {
    return id;
  }

  public void setId( Integer id )
  {
    this.id = id;
  }

  public StrategyTypeName getName()
  {
    return name;
  }

  public void setName( StrategyTypeName name )
  {
    this.name = name;
  }

  public List<Strategy> getStrategies()
  {
    return strategies;
  }

  public void setStrategies( List<Strategy> strategies )
  {
    this.strategies = strategies;
  }
}
