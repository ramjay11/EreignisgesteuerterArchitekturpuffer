package com.ramjava.produkt.dienstleistung;

import com.ramjava.produkt.dienstleistung.command.api.exception.ProduktServiceEventErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProduktDienstleistungApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProduktDienstleistungApplication.class, args);
	}

	// Register ExceptionHandler
//	@Autowired
//	public void configure(EventProcessingConfigurer configurer) {
//		configurer.registerListenerInvocationErrorHandler("produkt",
//				configuration -> new ProduktServiceEventErrorHandler());
//	}
}
