package com.adayazilim.sigorta.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyNumber;
    private Integer plateCityCode;
    private String plateCode;
    private String brand;
    private String model;
    private String year;
    private String engineNumber;
    private String chassisNumber;

    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
}
