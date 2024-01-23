package com.ramjava.produkt.dienstleistung.command.api.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data @Builder
public class ProduktRestModel {
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
