package com.adayazilim.sigorta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class VehicleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "vehicleInfo")
    private Policy policy;

    private Integer plateCityCode;
    private String plateCode;
    private String brand;
    private String model;
    private String year;
    private String engineNumber;
    private String chassisNumber;




}
