package com.adayazilim.sigorta.controller;

import com.adayazilim.sigorta.dto.VehicleDetailsDto;
import com.adayazilim.sigorta.service.VehicleCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleCodeController {


    private final VehicleCodeService vehicleCodeService;

    @GetMapping("/vehicle-code")
    public ResponseEntity<VehicleDetailsDto> getVehicleDetails(
            @RequestParam String year,
            @RequestParam String make,
            @RequestParam String model) {
        VehicleDetailsDto details = vehicleCodeService.getVehicleDetails(year, make, model);
        return ResponseEntity.ok(details);
    }

    public VehicleCodeController(VehicleCodeService vehicleCodeService) {
        this.vehicleCodeService = vehicleCodeService;
    }


    @GetMapping("/years")
    public ResponseEntity<List<String>> getYears() {
        List<String> years = vehicleCodeService.getAllYears();
        return ResponseEntity.ok(years);
    }

    @GetMapping("/makes")
    public ResponseEntity<List<String>> getMakes() {
        List<String> makes = vehicleCodeService.getAllMakes();
        return ResponseEntity.ok(makes);
    }

    @GetMapping("/models")
    public ResponseEntity<List<String>> getModels(@RequestParam String make) {
        List<String> models = vehicleCodeService.getModelsByMake(make);
        return ResponseEntity.ok(models);
    }
}