package com.adayazilim.sigorta.service;

import com.adayazilim.sigorta.dto.CreateVehicleDto;
import com.adayazilim.sigorta.entity.Vehicle;
import com.adayazilim.sigorta.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {

    private final VehicleRepository vehicleRepository;
    private final PolicyService policyService;


    public InsuranceService(VehicleRepository vehicleRepositoryRepository, PolicyService policyService) {
        this.vehicleRepository = vehicleRepositoryRepository;
        this.policyService = policyService;
    }

    public void createVehicle(Vehicle vehicle, Long customerId, double amount) {

       String policyNumber = policyService.createPolicy("310", customerId, amount);
       vehicle.setPolicyNumber(policyNumber);
       vehicleRepository.save(vehicle);

    }

}
