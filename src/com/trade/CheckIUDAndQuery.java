package com.trade;

import com.avaje.ebean.Ebean;

public class CheckIUDAndQuery {

  public static void main(String[] args) {

    //System.setProperty("catalina.base", "D:/apps/tomcat6");
    //System.setProperty("ebean.props.file", "D:/apps/tomcat6/conf/zsite.ebean.properties");
    //GlobalProperties.put("ebean.debug.sql", "true");

    EBasicVer e = new EBasicVer();
    e.setName("test");
    e.setDescription("something");

    // will insert
    Ebean.save(e);

    e.setDescription("changed");

    // this will update
    Ebean.save(e);

    // find the inserted entity by its id
    EBasicVer e2 = Ebean.find(EBasicVer.class, e.getId());
    System.out.println("Got "+e2.getDescription());

    Ebean.delete(e);
    // can use delete by id when you don't have the bean
    //Ebean.delete(ESimple.class, e.getId());

  }
}