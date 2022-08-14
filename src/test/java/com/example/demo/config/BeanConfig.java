package com.example.demo.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
    public RequestSpecBuilder requestSpecBuilder(){
        return new RequestSpecBuilder();
    }
}
