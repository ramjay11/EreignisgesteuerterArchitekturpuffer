package com.ramjava.paymentservice.command.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ZahlungRepo extends JpaRepository<Zahlung, String> {
}
