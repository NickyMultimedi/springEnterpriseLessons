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
    requires java.sql;
    requires java.persistence;
    // Spring Data
    requires spring.data.jpa;
    requires spring.data.commons;
    // Spring Security
    requires spring.boot.starter.security;
    requires spring.security.core;
    requires spring.security.config;

    // Spring Boot Test Bug Fix so everything keeps on working
    // Why Though???
    requires net.bytebuddy;
    requires java.xml.bind;
}
