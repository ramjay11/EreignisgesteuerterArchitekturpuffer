package com.ramjava.userservice.controller;

import com.ramjava.commonservice.models.Benutzer;
import com.ramjava.commonservice.query.GetUserZahlungDetailsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/benutzer")
public class BenutzerController {
    private QueryGateway queryGateway;

    @Autowired
    public BenutzerController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("{userId}")
    public Benutzer getBenutzer(@PathVariable String userId) {
        var getUserZahlungDetailsQuery = new GetUserZahlungDetailsQuery(userId);
        return queryGateway.query(getUserZahlungDetailsQuery, ResponseTypes.instanceOf(Benutzer.class)).join();
    }
}
