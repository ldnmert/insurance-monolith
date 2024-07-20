package com.adayazilim.sigorta.repository;

import com.adayazilim.sigorta.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
