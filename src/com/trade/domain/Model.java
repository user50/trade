package com.trade.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(discriminatorType= DiscriminatorType.CHAR)
public abstract class Model
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private List<Operation> operations = new ArrayList<Operation>(  );

  public List<Operation> getOperations()
  {
    return operations;
  }

  public void setOperations( List<Operation> operations )
  {
    this.operations = operations;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId( Integer id )
  {
    this.id = id;
  }
}
