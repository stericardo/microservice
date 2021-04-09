package com.microservice.api.todo.list;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ServletInitializerTest {

    @Test
    @DisplayName("Api test swagger - Configuration")
    @Order(1)
    public void apiTest(@Mock SpringApplicationBuilder application)  {
        when(application.sources(any())).thenReturn(application);
        ServletInitializer servletInitializer = new ServletInitializer();
        SpringApplicationBuilder springApplicationBuilderResult = servletInitializer.configure(application);
        Assert.assertNotNull(springApplicationBuilderResult);
    }
}
