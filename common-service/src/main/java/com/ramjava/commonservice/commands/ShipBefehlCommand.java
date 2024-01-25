package com.ramjava.commonservice.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data @Builder
public class ShipBefehlCommand {
    @TargetAggregateIdentifier // Every command should have this annotation
    private String shipmentId;
    private String orderId;
}
