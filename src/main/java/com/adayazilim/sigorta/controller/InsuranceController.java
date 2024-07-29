package com.adayazilim.sigorta.controller;

import com.adayazilim.sigorta.dto.CreateVehicleDto;
import com.adayazilim.sigorta.dto.PolicyDetailDto;
import com.adayazilim.sigorta.entity.Vehicle;
import com.adayazilim.sigorta.repository.VehicleCodeRepository;
import com.adayazilim.sigorta.service.InsuranceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;
    private final VehicleCodeRepository vehicleCodeRepository;

    public InsuranceController(InsuranceService insuranceService, VehicleCodeRepository vehicleCodeRepository) {
        this.insuranceService = insuranceService;
        this.vehicleCodeRepository = vehicleCodeRepository;
    }

    @PostMapping("/car")
    public ResponseEntity<PolicyDetailDto> createVehicle(@RequestBody CreateVehicleDto createVehicleDto, @RequestParam Long customerId, @RequestParam String vehicleCode) {

        Vehicle vehicle =  CreateVehicleDto.toVehicle(createVehicleDto);


        return ResponseEntity.ok(PolicyDetailDto.toDto(insuranceService.createVehicle(vehicle, customerId, vehicleCode)));


    }


}
