package com.ramjava.produkt.dienstleistung.query.api.projection;

import com.ramjava.produkt.dienstleistung.command.api.data.Produkt;
import com.ramjava.produkt.dienstleistung.command.api.data.ProduktRepository;
import com.ramjava.produkt.dienstleistung.command.api.model.ProduktRestModel;
import com.ramjava.produkt.dienstleistung.query.api.queries.GetProdukteQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdukteProjection {
    private ProduktRepository repository;

    public ProdukteProjection(ProduktRepository repository) {
        this.repository = repository;
    }
    @QueryHandler
    public List<ProduktRestModel> handle(GetProdukteQuery getProdukteQuery) {
        List<Produkt> produkte = repository.findAll();
        List<ProduktRestModel> produktRestModels = new ArrayList<>();
        for (Produkt produkt : produkte) {
            ProduktRestModel build = ProduktRestModel
                    .builder()
                    .name(produkt.getName())
                    .price(produkt.getPrice())
                    .quantity(produkt.getQuantity())
                    .build();
            produktRestModels.add(build);
        }
        return produktRestModels;
    }
}
