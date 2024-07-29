package com.adayazilim.sigorta.dto;

import com.adayazilim.sigorta.entity.Policy;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PolicyDetailDto {

    private String branchCode;
    private double amount;
    private LocalDate startDate;
    private char status;
    private LocalDate endDate;
    private String policyNumber;
    private CustomerDetailDto customerDetailDto;


    public static PolicyDetailDto toDto(Policy policy ) {
        PolicyDetailDto dto = new PolicyDetailDto();
        dto.setBranchCode(policy.getBranchCode());
        dto.setAmount(policy.getAmount());
        dto.setStartDate(policy.getStartDate());
        dto.setEndDate(policy.getEndDate());
        dto.setPolicyNumber(policy.getPolicyNumber());
        dto.setCustomerDetailDto(CustomerDetailDto.toDto(policy.getCustomer()));
        dto.setStatus(policy.getStatus());
        return dto;
    }

    public static List<PolicyDetailDto> toDtoList(List<Policy> policies) {
        List<PolicyDetailDto> dtos = new ArrayList<>();
        for (Policy policy : policies) {
            dtos.add(toDto(policy));

        }
        return dtos;
    }

}
