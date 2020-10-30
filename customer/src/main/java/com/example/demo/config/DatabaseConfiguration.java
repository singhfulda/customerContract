package com.example.demo.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    private String JDBC_URL;
    @Value("${spring.datasource.data-username}")
    private String USERNAME;
    @Value("${spring.datasource.data-password}")
    private String PASSWORD;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(JDBC_URL);
        dataSourceBuilder.username(USERNAME);
        dataSourceBuilder.password(PASSWORD);
        return dataSourceBuilder.build();
    }
}
