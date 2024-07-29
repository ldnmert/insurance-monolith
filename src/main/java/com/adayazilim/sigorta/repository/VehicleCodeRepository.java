package com.adayazilim.sigorta.repository;

import com.adayazilim.sigorta.entity.VehicleCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleCodeRepository extends JpaRepository<VehicleCode, Long> {

    @Query("SELECT DISTINCT v.year FROM VehicleCode v")
    List<String> findDistinctYears();

    @Query("SELECT DISTINCT v.make FROM VehicleCode v")
    List<String> findDistinctMakes();

    @Query("SELECT DISTINCT v.model FROM VehicleCode v WHERE v.make = :make")
    List<String> findModelsByMake(@Param("make") String make);

    VehicleCode findByYearAndMakeAndModel(String year, String make, String model);
    VehicleCode findByVehicleCode(String vehicleCode);
}