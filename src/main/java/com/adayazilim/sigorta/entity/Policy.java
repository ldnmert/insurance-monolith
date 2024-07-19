package com.adayazilim.sigorta.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;


    private String policyNumber;


    private char status = 'T';
    private String branchCode;
    private double amount;
    private LocalDate startDate;

    private LocalDate endDate;

    @PrePersist
    public void prePersist() {
        this.startDate = LocalDate.now();
        this.endDate = this.startDate.plusDays(15);
    }



}
