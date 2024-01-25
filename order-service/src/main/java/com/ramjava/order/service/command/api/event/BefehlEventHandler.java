package com.ramjava.order.service.command.api.event;

import com.ramjava.commonservice.event.BefehlCompletedEvent;
import com.ramjava.order.service.command.api.data.Befehl;
import com.ramjava.order.service.command.api.data.BefehlRepo;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BefehlEventHandler {
    private BefehlRepo repo;

    public BefehlEventHandler(BefehlRepo repo) {
        this.repo = repo;
    }

    @EventHandler
    public void on(BefehlCreatedEvent event) {
        var befehl = new Befehl();
        BeanUtils.copyProperties(event, befehl);
        repo.save(befehl);
    }
    @EventHandler
    public void on(BefehlCompletedEvent event) {
        Befehl befehl = repo.findById(event.getOrderId()).get();
        befehl.setOrderStatus(event.getOrderStatus());
        repo.save(befehl);
    }
}
