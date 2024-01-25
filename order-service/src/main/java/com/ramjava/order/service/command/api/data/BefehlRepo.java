package com.ramjava.order.service.command.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BefehlRepo extends JpaRepository<Befehl, String> {
}
