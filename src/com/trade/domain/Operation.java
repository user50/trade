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
  Strategy strategy;

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

//  public Strategy getStrategy()
//  {
//    return strategy;
//  }
//
//  public void setStrategy( Strategy strategy )
//  {
//    this.strategy = strategy;
//  }

  public Bar getBar()
  {
    return bar;
  }

  public void setBar( Bar bar )
  {
    this.bar = bar;
  }

}
