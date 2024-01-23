package com.ramjava.produkt.dienstleistung.query.api.controller;

import com.ramjava.produkt.dienstleistung.command.api.model.ProduktRestModel;
import com.ramjava.produkt.dienstleistung.query.api.queries.GetProdukteQuery;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produkte")
public class ProduktQueryController {
    private QueryGateway queryGateway;

    public ProduktQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProduktRestModel> getAllProdukte() {
        var getProdukteQuery = new GetProdukteQuery();
        List<ProduktRestModel> produktRestModels = queryGateway.query(getProdukteQuery,
                ResponseTypes.multipleInstancesOf(ProduktRestModel.class))
                .join();
        return produktRestModels;
    }
}
