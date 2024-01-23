package com.ramjava.produkt.dienstleistung.command.api.aggregate;

import com.ramjava.produkt.dienstleistung.command.api.commands.CreateProduktCommand;
import com.ramjava.produkt.dienstleistung.command.api.events.ProduktCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import java.math.BigDecimal;

//
// Aggregate is the command handler, Projection is the query handler
@Aggregate
public class ProduktAggregate {
    @AggregateIdentifier
    private String produkteId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProduktAggregate() {
    }
    @CommandHandler
    public ProduktAggregate(CreateProduktCommand createProduktCommand) {
        // Perform validation
        var produkteCreatedEvent = new ProduktCreatedEvent();
        BeanUtils.copyProperties(createProduktCommand, produkteCreatedEvent);
        AggregateLifecycle.apply(produkteCreatedEvent);
    }

    // Event handler
    @EventSourcingHandler
    public void on(ProduktCreatedEvent produkteCreatedEvent) {
        this.produkteId = produkteCreatedEvent.getProduktId();
        this.name = produkteCreatedEvent.getName();
        this.price = produkteCreatedEvent.getPrice();
        this.quantity = produkteCreatedEvent.getQuantity();
    }

}
