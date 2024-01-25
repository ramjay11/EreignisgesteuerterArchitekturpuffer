package com.ramjava.userservice.config;

import org.axonframework.serialization.Serializer;
import org.axonframework.config.Configurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Autowired
    public void configure(Serializer serializer, Configurer configurer) {
        configurer.configureSerializer(c -> serializer);
    }
}