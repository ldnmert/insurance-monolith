package com.adayazilim.sigorta.service;

import com.adayazilim.sigorta.dto.CreateVehicleDto;
import com.adayazilim.sigorta.entity.Policy;
import com.adayazilim.sigorta.entity.Vehicle;
import com.adayazilim.sigorta.entity.VehicleCode;
import com.adayazilim.sigorta.repository.VehicleCodeRepository;
import com.adayazilim.sigorta.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {

    private final VehicleRepository vehicleRepository;
    private final PolicyService policyService;
    private final VehicleCodeRepository vehicleCodeRepository;


    public InsuranceService(VehicleRepository vehicleRepositoryRepository, PolicyService policyService, VehicleCodeRepository vcodeRepository, VehicleCodeRepository vehicleCodeRepository) {
        this.vehicleRepository = vehicleRepositoryRepository;
        this.policyService = policyService;
        this.vehicleCodeRepository = vehicleCodeRepository;
    }


    public Policy createVehicle(Vehicle vehicle, Long customerId, String carCode) {

        VehicleCode vehicleCode = vehicleCodeRepository.findByVehicleCode(carCode);

       Policy policy = policyService.createPolicy("310", customerId, vehicleCode.getAmount());
       vehicle.setPolicyNumber(policy.getPolicyNumber());
       vehicle.setModel(vehicleCode.getModel());
       vehicle.setBrand(vehicleCode.getMake());
       vehicle.setYear(vehicleCode.getYear());
       vehicleRepository.save(vehicle);
        return policy;
    }

}
