package com.trade;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main
{
  public static void main( String[] args ) throws ParseException
  {
    String dateString = "2013.07.01 07:23";
    SimpleDateFormat dateFormat = new SimpleDateFormat(  );
    dateFormat.parse( dateString );

  }
}
