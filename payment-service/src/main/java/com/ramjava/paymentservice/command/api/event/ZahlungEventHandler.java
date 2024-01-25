package com.ramjava.paymentservice.command.api.event;

import com.ramjava.commonservice.event.ZahlungProcessedEvent;
import com.ramjava.paymentservice.command.api.data.Zahlung;
import com.ramjava.paymentservice.command.api.data.ZahlungRepo;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ZahlungEventHandler {
    private ZahlungRepo repo;

    public ZahlungEventHandler(ZahlungRepo repo) {
        this.repo = repo;
    }

    @EventHandler
    public void on(ZahlungProcessedEvent event) {
        var zahlung = Zahlung.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .paymentStatus("COMPLETED")
                .timestamp(new Date())
                .build();
        repo.save(zahlung);
    }
}
