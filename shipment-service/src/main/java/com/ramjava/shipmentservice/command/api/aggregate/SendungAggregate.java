package com.ramjava.shipmentservice.command.api.aggregate;

import com.ramjava.commonservice.event.BefehlShippedEvent;
import com.ramjava.commonservice.commands.ShipBefehlCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class SendungAggregate {
    @AggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;

    public SendungAggregate() {
    }
    @CommandHandler
    public SendungAggregate(ShipBefehlCommand shipBefehlCommand) {
        // Validate the command then publish BefehlShippedEvent
        var befehlShippedEvent = BefehlShippedEvent.builder()
                .shipmentId(shipBefehlCommand.getShipmentId())
                .orderId(shipBefehlCommand.getOrderId())
                .shipmentStatus("COMPLETED")
                .build();
        AggregateLifecycle.apply(befehlShippedEvent);
    }
    @EventSourcingHandler
    public void on(BefehlShippedEvent event) {
        this.orderId = event.getOrderId();
        this.shipmentId = event.getShipmentId();
        this.shipmentStatus = event.getShipmentStatus();
    }
}
