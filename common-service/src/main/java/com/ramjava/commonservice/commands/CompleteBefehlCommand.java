package com.ramjava.commonservice.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data @Builder
public class CompleteBefehlCommand {
    @TargetAggregateIdentifier
    private String orderId;
    private String orderStatus;

}
