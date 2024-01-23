package com.ramjava.produkt.dienstleistung.command.api.events;

import com.ramjava.produkt.dienstleistung.command.api.data.Produkt;
import com.ramjava.produkt.dienstleistung.command.api.data.ProduktRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component @ProcessingGroup("produkt")
public class ProduktEventHandler {
    private ProduktRepository repository;

    public ProduktEventHandler(ProduktRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ProduktCreatedEvent event) {
        var produkte = new Produkt();
        BeanUtils.copyProperties(event, produkte);
        repository.save(produkte);
    }
//    @EventHandler
//    public void on(ProduktCreatedEvent event) throws Exception {
//        var produkte = new Produkt();
//        BeanUtils.copyProperties(event, produkte);
//        repository.save(produkte);
//        throw new Exception("Es ist eine Ausnahme aufgetreten");
//    }
    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
