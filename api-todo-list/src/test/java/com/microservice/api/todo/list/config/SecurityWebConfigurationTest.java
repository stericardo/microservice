package com.microservice.api.todo.list.config;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class SecurityWebConfigurationTest {

    @Test
    @DisplayName("Api test swagger - Configuration")
    @Order(1)
    public void apiTest()  {
        SecurityWebConfiguration securityWebConfiguration = new SecurityWebConfiguration();
        WebMvcConfigurer webMvcConfigurer = securityWebConfiguration.corsConfigurer();
        Assert.assertNotNull(webMvcConfigurer);
    }
}
