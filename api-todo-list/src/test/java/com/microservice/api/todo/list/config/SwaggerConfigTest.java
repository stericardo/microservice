package com.microservice.api.todo.list.config;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfigTest {

    @Test
    @DisplayName("Api test swagger - Configuration")
    @Order(1)
    public void apiTest()  {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        Docket docket =swaggerConfig.api();
        Assert.assertNotNull(docket);
    }
}
