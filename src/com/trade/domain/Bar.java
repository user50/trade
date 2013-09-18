package com.trade.domain;

import com.avaje.ebean.Ebean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Bar
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  Date date;

  Double open;
  Double high;
  Double low;
  Double close;
  Integer volume;

  @ManyToOne
  FinancialInstrument instrument;

  @OneToMany(cascade = CascadeType.ALL)
  List<Indicator> indicators = new ArrayList<Indicator>(  );

  public Integer getId()
  {
    return id;
  }

  public void setId( Integer id )
  {
    this.id = id;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate( Date date )
  {
    this.date = date;
  }

  public Double getOpen()
  {
    return open;
  }

  public void setOpen( Double open )
  {
    this.open = open;
  }

  public Double getHigh()
  {
    return high;
  }

  public void setHigh( Double high )
  {
    this.high = high;
  }

  public Double getLow()
  {
    return low;
  }

  public void setLow( Double low )
  {
    this.low = low;
  }

  public Double getClose()
  {
    return close;
  }

  public void setClose( Double close )
  {
    this.close = close;
  }

  public Integer getVolume()
  {
    return volume;
  }

  public void setVolume( Integer volume )
  {
    this.volume = volume;
  }

  public FinancialInstrument getInstrument()
  {
    return instrument;
  }

  public void setInstrument( FinancialInstrument instrument )
  {
    this.instrument = instrument;
  }

  public List<Indicator> getIndicators()
  {
    return indicators;
  }

  public void setIndicators( List<Indicator> indicators )
  {
    this.indicators = indicators;
  }

  public static List<Bar> get(int finInstrumentId )
  {
    return Ebean.find( Bar.class ).where().eq( "instrument_id", finInstrumentId ).setOrderBy( "date" ).findList();
  }
}
