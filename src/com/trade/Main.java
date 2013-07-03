package com.trade;

import com.avaje.ebean.Ebean;
import com.trade.domain.Bar;
import com.trade.domain.FinancialInstrument;
import com.trade.domain.StrategyType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    StrategyType strategyType = new StrategyType();

    strategyType.setName( StrategyType.StrategyTypeName.dynamicGammaPoisson );

    Ebean.save( strategyType );

    StrategyType foundStrategyType = Ebean.find( StrategyType.class ).findUnique();
  }
}
