package com.trade.domain;

import javax.persistence.*;

@Entity
public class DynamicGammaPoissonIndicator
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  Double mean;

  Double dispersion;

  @ManyToOne
  Model model;

  public DynamicGammaPoissonIndicator()
  {
  }

  public DynamicGammaPoissonIndicator( Double mean, Double dispersion )
  {
    this.mean = mean;
    this.dispersion = dispersion;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId( Integer id )
  {
    this.id = id;
  }

  public Double getMean()
  {
    return mean;
  }

  public void setMean( Double mean )
  {
    this.mean = mean;
  }

  public Double getDispersion()
  {
    return dispersion;
  }

  public void setDispersion( Double dispersion )
  {
    this.dispersion = dispersion;
  }

  public Model getModel()
  {
    return model;
  }

  public void setModel( Model model )
  {
    this.model = model;
  }
}
