package com.trade.domain;

import javax.persistence.*;

@Entity
public class Operation
{
  public static enum OperationType
  {
    buy, sell
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  OperationType operation;

  @ManyToOne
  Model strategy;

  @ManyToOne
  Bar bar;

  public Integer getId()
  {
    return id;
  }

  public void setId( Integer id )
  {
    this.id = id;
  }

  public OperationType getOperation()
  {
    return operation;
  }

  public void setOperation( OperationType operation )
  {
    this.operation = operation;
  }

  public Model getStrategy()
  {
    return strategy;
  }

  public void setStrategy( Model strategy )
  {
    this.strategy = strategy;
  }

  public Bar getBar()
  {
    return bar;
  }

  public void setBar( Bar bar )
  {
    this.bar = bar;
  }

}
