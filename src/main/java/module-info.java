open module be.multimedi.lessons.springadvanced {
    requires spring.core;
    requires spring.context;
    requires spring.beans;
    requires spring.jdbc;
    requires spring.tx;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.boot.starter.jdbc;
    requires spring.boot.starter.data.jpa;
    requires spring.web;
    requires java.sql;
    requires java.persistence;
    requires java.validation;
    // Spring Data
    requires spring.data.jpa;
    requires spring.data.commons;
    // Spring Security
    requires spring.boot.starter.security;
    requires spring.security.core;
    requires spring.security.config;

    // Servlet systems
    requires tomcat.embed.core;

    // Spring Boot Test Bug Fix so everything keeps on working
    // Why Though??? Is in lower packages and isnt modulair yet, so we need to make it this way. Need these for Jaxb anyway.
    requires net.bytebuddy;
    requires java.xml.bind;
    //XML
    requires com.sun.xml.bind;
    //requires java.xml.bind;

    //JSON
    requires jackson.annotations;

}
