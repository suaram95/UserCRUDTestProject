package com.example.user.crud.test.project.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapConfiguration {

    @Bean
    public ModelMapper modelMap(){
        return new ModelMapper();
    }
}
