package com.ramjava.userservice.projection;

import com.ramjava.commonservice.models.Benutzer;
import com.ramjava.commonservice.models.CardDetails;
import com.ramjava.commonservice.query.GetUserZahlungDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class BenutzerProjection {
    @QueryHandler
    public Benutzer getBenutzerZahlungDetails(GetUserZahlungDetailsQuery query) {
        // Get details from DB
        var cardDetails = CardDetails.builder()
                .name("Huskey Siber")
                .validUntilMonth(10)
                .validUntilYear(2023)
                .cardNumber("78475784548")
                .cvv(227)
                .build();
        return Benutzer.builder()
                .userId(query.getUserId())
                .firstName("Huskey")
                .lastName("Saint")
                .cardDetails(cardDetails)
                .build();
    }
}
