package com.ramjava.order.service.command.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class BefehlRestModel {
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
}
