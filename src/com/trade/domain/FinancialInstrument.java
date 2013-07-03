package com.trade.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class FinancialInstrument
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  String name;

  @OneToMany(cascade = CascadeType.ALL)
  ArrayList<Bar> bars;

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

  public ArrayList<Bar> getBars()
  {
    return bars;
  }

  public void setBars( ArrayList<Bar> bars )
  {
    this.bars = bars;
  }

  public void add(Bar bar)
  {
    if( bars == null )
      bars = new ArrayList<Bar>(  );

    bars.add( bar );
  }
}
