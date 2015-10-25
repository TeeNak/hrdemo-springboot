package com.github.teenak77.hrdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class WebUiApplication {

    @Autowired
    private DataSourceProperties properties;

    @Bean
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        ds.setDriverClassName(this.properties.getDriverClassName());
        ds.setUrl(this.properties.getUrl());
        ds.setUsername(this.properties.getUsername());
        ds.setPassword(this.properties.getPassword());
        return ds;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebUiApplication.class, args);
    }
}
