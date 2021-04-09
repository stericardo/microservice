package com.microservice.api.todo.list.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import java.util.Optional;

@SpringBootConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.microservice.api.todo.list.repositories"})
@EntityScan( basePackages = {"com.microservice.api.todo.list.entity"} )
@Profile({"development", "production"})
public class DatabaseConfiguration {

    protected final Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);

    private final String OS = System.getProperty("os.name").toLowerCase();

    @Value("${spring.datasource.username}")
    protected String userName;

    @Value("${spring.datasource.url}")
    protected String dbUrl;

    @Value("${spring.datasource.driver-class-name}")
    protected String driverClassName;

    @Value("${windows.pgpass}")
    protected String WINDOWS_PGPASS;

    @Value("${other.os.pgpass}")
    protected String OTHER_OS_PGPASS;

}
