package com.ramjava.order.service.command.api.aggregate;

import com.ramjava.commonservice.commands.CompleteBefehlCommand;
import com.ramjava.commonservice.event.BefehlCompletedEvent;
import com.ramjava.order.service.command.api.command.CreateBefehlCommand;
import com.ramjava.order.service.command.api.event.BefehlCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class BefehlAggregate {
    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

    public BefehlAggregate() {
    }
    @CommandHandler
    public BefehlAggregate(CreateBefehlCommand createBefehlCommand) {
        // Validate the command
        var befehlCreatedEvent = new BefehlCreatedEvent();
        BeanUtils.copyProperties(createBefehlCommand, befehlCreatedEvent);
        AggregateLifecycle.apply(befehlCreatedEvent);
    }
    @EventSourcingHandler
    public void on(BefehlCreatedEvent event) {
        this.orderId = event.getOrderId();
        this.userId = event.getUserId();
        this.productId = event.getProductId();
        this.addressId = event.getAddressId();
        this.orderStatus = event.getOrderStatus();
    }

    @CommandHandler
    public void handle(CompleteBefehlCommand completeBefehlCommand) {
        // Validate command then publish BefehlCompletedEvent
        var befehlCompletedEvent = BefehlCompletedEvent.builder()
                .orderId(completeBefehlCommand.getOrderId())
                .orderStatus(completeBefehlCommand.getOrderStatus())
                .build();
        AggregateLifecycle.apply(befehlCompletedEvent);
    }
    @EventSourcingHandler
    public void on(BefehlCompletedEvent event) {
        this.orderStatus = event.getOrderStatus();
    }
}
