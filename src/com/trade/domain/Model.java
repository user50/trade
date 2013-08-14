package com.trade.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Model
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Indicator> indicators = new ArrayList<Indicator>(  );

  @OneToMany(cascade = CascadeType.ALL)
  private List<Parameter> parameters = new ArrayList<Parameter>(  );

  @ManyToOne
  private ModelType type;

  public List<Indicator> getIndicators()
  {
    return indicators;
  }

  public void setIndicators( List<Indicator> indicators )
  {
    this.indicators = indicators;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId( Integer id )
  {
    this.id = id;
  }

  public List<Parameter> getParameters()
  {
    return parameters;
  }

  public void setParameters( List<Parameter> parameters )
  {
    this.parameters = parameters;
  }

  public ModelType getType()
  {
    return type;
  }

  public void setType( ModelType type )
  {
    this.type = type;
  }

  public void add(Parameter parameter)
  {
    parameters.add( parameter );
  }

  public void add(Indicator indicator)
  {
    indicators.add( indicator );
  }
}
