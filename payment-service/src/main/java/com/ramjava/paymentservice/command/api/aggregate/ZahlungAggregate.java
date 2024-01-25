package com.ramjava.paymentservice.command.api.aggregate;

import com.ramjava.commonservice.commands.ValidateZahlungCommand;
import com.ramjava.commonservice.event.ZahlungProcessedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate @Slf4j
public class ZahlungAggregate {
    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus;

    public ZahlungAggregate() {
    }
    @CommandHandler
    public ZahlungAggregate(ValidateZahlungCommand validateZahlungCommand) {
        // Validate payment details then publish ZahlungProcessedEvent
        log.info("Executing ValidateZahlungCommand for " + "Order Id: {} and Payment Id: {}",
                validateZahlungCommand.getOrderId(),
                validateZahlungCommand.getPaymentId());
        var zahlungProcessedEvent = new ZahlungProcessedEvent(
                validateZahlungCommand.getPaymentId(), validateZahlungCommand.getOrderId()
        );
        AggregateLifecycle.apply(zahlungProcessedEvent);
        log.info("ZahlungProcessedEvent applied");
    }
    @EventSourcingHandler
    public void on(ZahlungProcessedEvent event) {
        this.paymentId = event.getPaymentId();
        this.orderId = event.getOrderId();
    }
}
