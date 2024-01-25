package com.ramjava.order.service.command.api.controller;

import com.ramjava.order.service.command.api.command.CreateBefehlCommand;
import com.ramjava.order.service.command.api.model.BefehlRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auftrage")
public class BefehlCommandController {
    private CommandGateway commandGateway;

    public BefehlCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
    @PostMapping
    public String createOrder(@RequestBody BefehlRestModel befehlRestModel) {
        // publish to command gateway
        String orderId = UUID.randomUUID().toString();
        var createBefehlCommand = CreateBefehlCommand
                .builder()
                .orderId(orderId)
                .addressId(befehlRestModel.getAddressId())
                .productId(befehlRestModel.getProductId())
                .quantity(befehlRestModel.getQuantity())
                .orderStatus("CREATED")
                .build();
        commandGateway.sendAndWait(createBefehlCommand);
        return "Bestellung erstellt!";
    }
}
