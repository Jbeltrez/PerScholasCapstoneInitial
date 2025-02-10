package com.john.spring.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan (basePackages = "com.john.spring")
@EnableWebMvc
//will scan entire project
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper returnValue = new ModelMapper();

        return returnValue;
    }
}
