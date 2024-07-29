package com.adayazilim.sigorta.service;

import com.adayazilim.sigorta.dto.VehicleDetailsDto;
import com.adayazilim.sigorta.entity.VehicleCode;
import com.adayazilim.sigorta.repository.VehicleCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleCodeService {

    private final VehicleCodeRepository vehicleCodeRepository;

    public VehicleCodeService(VehicleCodeRepository vehicleCodeRepository) {
        this.vehicleCodeRepository = vehicleCodeRepository;

    }

    public VehicleDetailsDto getVehicleDetails(String year, String make, String model) {
        // Fetch details from the repository or service
        VehicleCode details = vehicleCodeRepository.findByYearAndMakeAndModel(year, make, model);

        VehicleDetailsDto dto = new VehicleDetailsDto();
        dto.setVehicleCode(details.getVehicleCode());
        dto.setAmount(details.getAmount());

        return dto;
    }

    public List<String> getAllYears() {
        return vehicleCodeRepository.findDistinctYears();
    }

    public List<String> getAllMakes() {
        return vehicleCodeRepository.findDistinctMakes();
    }

    public List<String> getModelsByMake(String make) {
        return vehicleCodeRepository.findModelsByMake(make);
    }

//    public VehicleCode getVehicleCode(int year, String make, String model) {
//        return vehicleCodeRepository.findByYearAndMakeAndModel(year, make, model);
//    }
}
