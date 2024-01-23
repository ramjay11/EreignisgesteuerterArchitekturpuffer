package com.ramjava.produkt.dienstleistung.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class ProduktCreatedEvent {
    @TargetAggregateIdentifier
    private String produktId; // Identifier
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
