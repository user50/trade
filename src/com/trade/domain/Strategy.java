package com.trade.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Strategy
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  @OneToMany(cascade = CascadeType.ALL)
  List<StrategyParameter> parameters = new ArrayList<StrategyParameter>(  );

  @OneToMany(cascade = CascadeType.ALL)
  List<Operation> operations = new ArrayList<Operation>(  );

  @ManyToOne
  StrategyType strategyType;

  public Integer getId()
  {
    return id;
  }

  public void setId( Integer id )
  {
    this.id = id;
  }

  public List<StrategyParameter> getParameters()
  {
    return parameters;
  }

  public void setParameters( List<StrategyParameter> parameters )
  {
    this.parameters = parameters;
  }

  public List<Operation> getOperations()
  {
    return operations;
  }

  public void setOperations( List<Operation> operations )
  {
    this.operations = operations;
  }

  public StrategyType getStrategyType()
  {
    return strategyType;
  }

  public void setStrategyType( StrategyType strategyType )
  {
    this.strategyType = strategyType;
  }
}
