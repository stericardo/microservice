package com.microservice.api.todo.list.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Profile("integration-test")
@Configuration
@ComponentScan(basePackages = "com.equitativa.task.management")
public class TestConfiguration {

    @Bean
    @Primary
    public DataSource dataSourceIntegration() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/integrationtest");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

}
