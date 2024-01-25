package com.ramjava.shipmentservice.command.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SendungRepo extends JpaRepository<Sendung, String> {
}
