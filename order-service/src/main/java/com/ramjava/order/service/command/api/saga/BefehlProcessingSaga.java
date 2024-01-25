package com.ramjava.order.service.command.api.saga;

import com.ramjava.commonservice.commands.CompleteBefehlCommand;
import com.ramjava.commonservice.commands.ValidateZahlungCommand;
import com.ramjava.commonservice.event.BefehlCompletedEvent;
import com.ramjava.commonservice.event.BefehlShippedEvent;
import com.ramjava.commonservice.commands.ShipBefehlCommand;
import com.ramjava.commonservice.event.ZahlungProcessedEvent;
import com.ramjava.commonservice.models.Benutzer;
import com.ramjava.commonservice.query.GetUserZahlungDetailsQuery;
import com.ramjava.order.service.command.api.event.BefehlCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

import java.util.UUID;


@Saga @Slf4j //@DependsOn("entityManagerFactory")
public class BefehlProcessingSaga {
    private CommandGateway commandGateway;
    private QueryGateway queryGateway;

    //@Autowired
    public BefehlProcessingSaga(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
    @Autowired
    public void setQueryGateway(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(BefehlCreatedEvent event) {
        log.info("BefehlCreatedEvent in Saga for Order Id : {}", event.getOrderId());
        var getUserZahlungDetailsQuery = new GetUserZahlungDetailsQuery(event.getUserId());
        Benutzer benutzer = null;

        try {
            benutzer = queryGateway.query(
                    getUserZahlungDetailsQuery, ResponseTypes.instanceOf(Benutzer.class)
            ).join();
        } catch (Exception e) {
            log.error(e.getMessage());
            // Start compensating transaction
        }
        var validateZahlungCommand = ValidateZahlungCommand.builder()
                .orderId(event.getOrderId())
                .cardDetails(benutzer.getCardDetails())
                .paymentId(UUID.randomUUID().toString())
                .build();
        commandGateway.sendAndWait(validateZahlungCommand);
    }
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(ZahlungProcessedEvent event) {
        log.info("ZahlungProcessedEvent in Saga for Order Id : {}", event.getOrderId());
        try {
            var shipBefehlCommand = ShipBefehlCommand.builder()
                    .shipmentId(UUID.randomUUID().toString())
                    .orderId(event.getOrderId())
                    .build();
            commandGateway.send(shipBefehlCommand);
        } catch (Exception e) {
            log.error(e.getMessage());
            // Start compensating transaction
        }
    }
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(BefehlShippedEvent event) {
        log.info("BefehlShippedEvent in Saga for Order Id : {}", event.getOrderId());
        var completeBefehlCommand = CompleteBefehlCommand.builder()
                .orderId(event.getOrderId())
                .orderStatus("APPROVED")
                .build();
        commandGateway.send(completeBefehlCommand);
    }
    @SagaEventHandler(associationProperty = "orderId") @EndSaga
    public void handle(BefehlCompletedEvent event) {
        log.info("BefehlCompletedEvent in Saga for Order Id : {}", event.getOrderId());
    }
}
