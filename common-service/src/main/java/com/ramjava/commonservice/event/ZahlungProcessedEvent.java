package com.ramjava.commonservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ZahlungProcessedEvent {
    private String paymentId;
    private String orderId;
}
