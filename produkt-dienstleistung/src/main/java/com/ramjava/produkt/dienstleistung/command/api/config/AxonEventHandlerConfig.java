package com.ramjava.produkt.dienstleistung.command.api.config;

import com.ramjava.produkt.dienstleistung.command.api.exception.ProduktServiceEventErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonEventHandlerConfig {
    @Autowired
    public void configureEventProcessing(EventProcessingConfigurer configurer) {
        configurer.registerListenerInvocationErrorHandler("produkt",
                configuration -> new ProduktServiceEventErrorHandler());
    }
}
