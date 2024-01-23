package com.ramjava.produkt.dienstleistung.command.api.controller;

import com.ramjava.produkt.dienstleistung.command.api.commands.CreateProduktCommand;
import com.ramjava.produkt.dienstleistung.command.api.model.ProduktRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController @RequestMapping("/produkte")
public class ProduktCommandController {
    private CommandGateway commandGateway;

    @Autowired
    public ProduktCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addProdukt(@RequestBody ProduktRestModel produktRestModel) {
        // Send to command gateway
        CreateProduktCommand createProduktCommand =
                CreateProduktCommand.builder()
                        .produktId(UUID.randomUUID().toString())
                        .name(produktRestModel.getName())
                        .price(produktRestModel.getPrice())
                        .quantity(produktRestModel.getQuantity())
                        .build();
        String result = commandGateway.sendAndWait(createProduktCommand);
        return result;
    }
}
