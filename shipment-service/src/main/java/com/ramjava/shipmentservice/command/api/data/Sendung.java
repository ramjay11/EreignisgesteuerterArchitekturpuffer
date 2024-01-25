package com.ramjava.shipmentservice.command.api.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data @Entity
public class Sendung {
    @Id
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;
}
