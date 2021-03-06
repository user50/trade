package com.trade.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class FinancialInstrument
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  Integer spread;
  Integer pips;
  Integer volumeMultiplier;

  String name;

  @OneToMany(cascade = CascadeType.ALL)
  List<Bar> bars;

  public FinancialInstrument( String name )
  {
    this.name = name;
  }

  public FinancialInstrument()
  {
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

  public List<Bar> getBars()
  {
    return bars;
  }

  public void setBars( List<Bar> bars )
  {
    this.bars = bars;
  }

  public Integer getSpread()
  {
    return spread;
  }

  public void setSpread( Integer spread )
  {
    this.spread = spread;
  }

  public Integer getPips()
  {
    return pips == null ? 4 : pips;
  }

  public void setPips( Integer pips )
  {
    this.pips = pips;
  }

  public Integer getVolumeMultiplier()
  {
    return volumeMultiplier;
  }

  public void setVolumeMultiplier( Integer volumeMultiplier )
  {
    this.volumeMultiplier = volumeMultiplier;
  }

  public void add(Bar bar)
  {
    if( bars == null )
      bars = new ArrayList<Bar>(  );

    bars.add( bar );
  }
}
