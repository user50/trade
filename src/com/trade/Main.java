package com.trade;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.FetchConfig;
import com.trade.domain.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Main
{
  public static void main( String[] args ) throws ParseException, IOException
  {
    new CSVImportService().importAllTimeSeries();

    DynamicGammaPoisson gammaPoisson = new DynamicGammaPoisson();

    gammaPoisson.setAlpha( 0.05 );
    gammaPoisson.setSign( -1 );
    gammaPoisson.setStartMean( 0.5);
    gammaPoisson.setStartDispersion( 0.01 );
    Ebean.save( gammaPoisson );

    FinancialInstrument financialInstrument = Ebean.find( FinancialInstrument.class ).where().eq( "name","EURUSD" ).findUnique();
    List<Bar> bars = Ebean.find( Bar.class ).fetch( "instrument" ).where().eq( "instrument.name","EURUSD" ).findList();
    financialInstrument.setBars( bars );

    gammaPoisson.calculateIndicator( financialInstrument );


  }
}
