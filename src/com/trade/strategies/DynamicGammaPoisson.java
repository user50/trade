package com.trade.strategies;

import java.util.ArrayList;
import java.util.List;

public class DynamicGammaPoisson
{

  public static void main( String[] args )
  {
    int[] totalNumber = new int[]{20,30,25,14,60};
    int[] positiveNumber = new int[]{15,20,20,13,40};

    Parameters parameters = new Parameters( 0.5, 0.01 );
    List<Parameters> params = new ArrayList<Parameters>(  );

    for(int i=0; i<totalNumber.length; i++)
    {
      params.add( update( parameters, totalNumber[i], positiveNumber[i], 0.1  ) );
    }
  }

  public static Parameters update(Parameters parameters, int totalCount, int positiveCount, double eta)
  {
    double gamma = parameters.mean/ parameters.dispersion;
    double improvedGamma = gamma + totalCount;

    double improvedMu = (parameters.mean * gamma + positiveCount)/improvedGamma;
    double improvedDispersion = parameters.mean/improvedGamma;

    return new Parameters(improvedMu, improvedDispersion + eta*(improvedMu * improvedMu + improvedDispersion ) );
  }



  public static class Parameters
  {
    public Parameters( double mean, double dispersion )
    {
      this.mean = mean;
      this.dispersion = dispersion;
    }

    public double mean;
    public double dispersion;
  }
}
