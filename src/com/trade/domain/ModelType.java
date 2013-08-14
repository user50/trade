package com.trade.domain;

import com.avaje.ebean.annotation.EnumMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public enum ModelType
{
  dynamicGammaPoisson, sma

}
