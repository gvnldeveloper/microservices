package com.finland.controller;

import com.finland.ws.client.AuthClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan(basePackages = {"com.finland"})
public class TestConfiguration {

    @Bean
    @Primary
    public AuthClient getAuthClient() {
        return mock(AuthClient.class);
    }
}
