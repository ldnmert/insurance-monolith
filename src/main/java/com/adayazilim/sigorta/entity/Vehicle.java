package com.adayazilim.sigorta.entity;

import jakarta.persistence.*;
import lombok.Data;

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




}
