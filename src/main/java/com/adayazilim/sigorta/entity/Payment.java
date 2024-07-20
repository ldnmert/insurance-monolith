package com.adayazilim.sigorta.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyNumber;

    private double amount;
    private LocalDate paymentDate;

    private String cardNumber;
    private String cvv;

    @PrePersist
    protected void onCreate() {
        this.paymentDate = LocalDate.now();
    }
}
