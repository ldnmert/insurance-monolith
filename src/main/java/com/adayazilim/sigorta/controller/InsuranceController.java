package com.adayazilim.sigorta.controller;

import com.adayazilim.sigorta.dto.CreateVehicleDto;
import com.adayazilim.sigorta.entity.Vehicle;
import com.adayazilim.sigorta.service.InsuranceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping("/car")
    public ResponseEntity<Void> createVehicle(@RequestBody CreateVehicleDto createVehicleDto, @RequestParam Long customerId, @RequestParam double amount) {

        Vehicle vehicle =  CreateVehicleDto.toVehicle(createVehicleDto);

        insuranceService.createVehicle(vehicle, customerId, amount);

        return ResponseEntity.ok().build();
    }


}
