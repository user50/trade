package com.trade;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.FetchConfig;
import com.trade.algorithms.DynamicGammaPoisson;
import com.trade.domain.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Main
{
  public static void main( String[] args ) throws ParseException, IOException
  {
    new CSVImportService().importAllTimeSeries();

    Model model = DynamicGammaPoisson.createDynamicGammaPoissonModel( 0.5,0.01,0.0001 );
    Ebean.save( model );

    FinancialInstrument financialInstrument = Ebean.find( FinancialInstrument.class ).where().eq( "name","EURUSD" ).findUnique();
    financialInstrument.setPips( 5 );
    financialInstrument.setVolumeMultiplier( 10 );
    Ebean.save( financialInstrument );

    List<Bar> bars = Ebean.find( Bar.class ).fetch( "instrument" ).where().eq( "instrument.name","EURUSD" ).setOrderBy( "date" ).findList();
    financialInstrument.setBars( bars );

    DynamicGammaPoisson.calculateIndicator( financialInstrument, model );

  }
}
