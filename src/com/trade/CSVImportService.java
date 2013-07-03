package com.trade;

import com.avaje.ebean.Ebean;
import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.reader.CSVEntryParser;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import com.trade.domain.Bar;
import com.trade.domain.FinancialInstrument;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVImportService
{
  private final static String FILE_EXTENSION = ".csv";
  private final static String EMPTY_STRING = "";
  private final static String FINANCIAL_INSTRUMENT_FOLDER = "currencies";

  public static void main( String[] args ) throws IOException
  {
    new CSVImportService().importAllTimeSeries();
  }

  public void importAllTimeSeries() throws IOException
  {
    File instrumentsFolder = new File( FINANCIAL_INSTRUMENT_FOLDER );
    for( File file : instrumentsFolder.listFiles() )
    {
      importTimeSeries( file );
    }

  }

  public void importTimeSeries(File instrumentFile) throws IOException
  {
    String instrumentName = instrumentFile.getName().replace( FILE_EXTENSION, EMPTY_STRING );

    FinancialInstrument instrument = Ebean.find( FinancialInstrument.class ).where().eq( "name", instrumentName ).findUnique();
    if( instrument == null )
      instrument = new FinancialInstrument(instrumentName);

    instrument.setBars( getBars( instrumentFile ) );

    Ebean.save( instrument );
  }

  private Iterator<Bar> getBarIterator(File instrumentFile) throws FileNotFoundException
  {
    return getCSVReader( instrumentFile ).iterator();
  }

  private List<Bar> getBars(File instrumentFile) throws IOException
  {
    return getCSVReader( instrumentFile ).readAll();
  }

  private CSVReader<Bar> getCSVReader(File instrumentFile) throws FileNotFoundException
  {
    Reader reader = new FileReader( instrumentFile );
    CSVStrategy strategy = new CSVStrategy( ',', '"', '#', false, true );

    return new CSVReaderBuilder<Bar>( reader ).strategy( strategy ).entryParser( new BarParser() ).build();
  }


  public static class BarParser implements CSVEntryParser<Bar>
  {

    @Override
    public Bar parseEntry( String... strings )
    {
      try
      {
        Bar bar = new Bar();

        SimpleDateFormat dateFormat = new SimpleDateFormat(  );
        bar.setDate( dateFormat.parse( strings[0]+" "+strings[1] ) );
        bar.setOpen( Double.parseDouble( strings[ 2 ] ) );
        bar.setHigh( Double.parseDouble( strings[ 3 ] ) );
        bar.setLow( Double.parseDouble( strings[ 4 ] ) );
        bar.setClose( Double.parseDouble( strings[ 5 ] ) );
        bar.setVolume( Integer.parseInt( strings[ 6 ] ) );

        return bar;
      }
      catch( ParseException e )
      {
        Logger.getLogger( "Trade" ).log( Level.WARNING, "Wrong date format" );

        throw new RuntimeException( e );
      }
    }
  }
}
