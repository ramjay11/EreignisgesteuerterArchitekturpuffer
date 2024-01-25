package com.ramjava.commonservice.event;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class BefehlCompletedEvent {
    private String orderId;
    private String orderStatus;
}
