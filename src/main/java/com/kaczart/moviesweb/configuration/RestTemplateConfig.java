package com.kaczart.moviesweb.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate provideRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }

}
