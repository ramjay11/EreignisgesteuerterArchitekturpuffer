package com.ramjava.commonservice.models;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Benutzer {
    private String userId;
    private String firstName;
    private String lastName;
    private CardDetails cardDetails;
}
