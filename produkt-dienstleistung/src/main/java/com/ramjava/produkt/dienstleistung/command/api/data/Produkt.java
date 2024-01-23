package com.ramjava.produkt.dienstleistung.command.api.data;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data @Entity
public class Produkt {
    @Id
    private String produktId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
