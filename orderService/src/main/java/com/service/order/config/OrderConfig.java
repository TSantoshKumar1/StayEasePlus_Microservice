package com.service.order.config;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {


    @Bean
    public ModelMapper modelMapper(){

       ModelMapper modelMapper = new ModelMapper();
       return modelMapper;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
