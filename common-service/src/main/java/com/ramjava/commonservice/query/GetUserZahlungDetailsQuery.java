package com.ramjava.commonservice.query;

import com.thoughtworks.xstream.XStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class GetUserZahlungDetailsQuery {
    private String userId;
}
