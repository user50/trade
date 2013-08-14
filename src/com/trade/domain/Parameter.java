package com.trade.domain;

import javax.persistence.*;

@Entity
public class Parameter
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;
  private String value;
  private String type;

  @ManyToOne
  private Model model;

  public Parameter()
  {
  }

  public Parameter( String name, String value, String type )
  {
    this.name = name;
    this.value = value;
    this.type = type;
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

  public String getValue()
  {
    return value;
  }

  public void setValue( String value )
  {
    this.value = value;
  }

  public String getType()
  {
    return type;
  }

  public void setType( String type )
  {
    this.type = type;
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
