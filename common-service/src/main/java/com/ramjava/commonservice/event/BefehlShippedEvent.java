package com.ramjava.commonservice.event;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class BefehlShippedEvent {
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;
}
