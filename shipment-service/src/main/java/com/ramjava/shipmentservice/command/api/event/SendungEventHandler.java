package com.ramjava.shipmentservice.command.api.event;

import com.ramjava.commonservice.event.BefehlShippedEvent;
import com.ramjava.shipmentservice.command.api.data.Sendung;
import com.ramjava.shipmentservice.command.api.data.SendungRepo;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SendungEventHandler {
    private SendungRepo repo;

    public SendungEventHandler(SendungRepo repo) {
        this.repo = repo;
    }

    // Save to DB
    @EventHandler
    public void on(BefehlShippedEvent event) {
        var sendung = new Sendung();
        BeanUtils.copyProperties(event, sendung);
        repo.save(sendung);
    }
}
