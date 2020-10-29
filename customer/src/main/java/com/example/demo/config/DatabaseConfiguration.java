package com.example.demo.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url: jdbc:h2:mem:test}")
    private String JDBC_H_2_MEM_TEST;
    @Value("${spring.datasource.driver-class-name: org.h2.Driver}")
    private String ORG_H_2_DRIVER;
    @Value("${spring.datasource.data-username: SA}")
    private  String USERNAME;
    @Value("${spring.datasource.data-password: }")
    private String PASSWORD;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(ORG_H_2_DRIVER);
        dataSourceBuilder.url(JDBC_H_2_MEM_TEST);
        dataSourceBuilder.username(USERNAME);
        dataSourceBuilder.password(PASSWORD);
        return dataSourceBuilder.build();
    }
}
