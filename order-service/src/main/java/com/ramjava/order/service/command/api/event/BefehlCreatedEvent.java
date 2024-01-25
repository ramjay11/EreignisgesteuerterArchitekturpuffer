package com.ramjava.order.service.command.api.event;

import lombok.Data;

@Data
public class BefehlCreatedEvent {
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;
}
