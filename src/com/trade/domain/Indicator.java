package com.trade.domain;

import javax.persistence.*;

@Entity
public class Indicator
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  String name;

  Double value;

  @ManyToOne
  Model model;

  @ManyToOne
  Bar bar;

  public Indicator()
  {
  }

  public Indicator( String name, Double value, Model model, Bar bar )
  {
    this.name = name;
    this.value = value;
    this.model = model;
    this.bar = bar;
  }

  public Model getModel()
  {
    return model;
  }

  public void setModel( Model model )
  {
    this.model = model;
  }

  public Bar getBar()
  {
    return bar;
  }

  public void setBar( Bar bar )
  {
    this.bar = bar;
  }

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


}
