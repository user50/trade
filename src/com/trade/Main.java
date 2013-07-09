package com.trade;

import com.avaje.ebean.Ebean;
import com.trade.domain.*;

import java.text.ParseException;
import java.util.Date;

public class Main
{
  public static void main( String[] args ) throws ParseException
  {
    FinancialInstrument instrument = new FinancialInstrument();
    instrument.setName( "EURUSD" );
    instrument.setSpread( 2 );
    instrument.setPips( 4 );

    Bar bar = new Bar();
    bar.setOpen( 1.20 );
    bar.setHigh( 1.23 );
    bar.setLow( 1.19 );
    bar.setClose( 1.21 );
    bar.setVolume( 1223 );
    bar.setDate( new Date(  ) );

    instrument.add( bar );
    bar.setInstrument( instrument );

    Ebean.save( instrument );

    DynamicGammaPoisson gammaPoisson = new DynamicGammaPoisson();

    gammaPoisson.setAlpha( 0.05 );
    gammaPoisson.setSign( -1 );
    Ebean.save( gammaPoisson );

    SimpleMovingAverage movingAverage = new SimpleMovingAverage();
    movingAverage.setAverageCount( 20 );
    Ebean.save( movingAverage );

  }
}
