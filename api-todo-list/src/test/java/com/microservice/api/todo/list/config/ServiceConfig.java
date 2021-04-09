package com.microservice.api.todo.list.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableJpaRepositories(basePackages = {"com.microservice.api.todo.list.repositories"})
@EntityScan( basePackages = {"com.microservice.api.todo.list.entity"})
@ComponentScan(basePackages = {"com.microservice.api.todo.list"})
public class ServiceConfig {
}
