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

    private String policyNo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_info_id", referencedColumnName = "id")
    private VehicleDetail vehicleInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;



    private char status;
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
