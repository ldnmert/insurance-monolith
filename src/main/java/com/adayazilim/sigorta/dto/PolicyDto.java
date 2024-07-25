package com.adayazilim.sigorta.dto;

import com.adayazilim.sigorta.entity.Policy;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PolicyDto {

    private String branchCode;
    private double amount;
    private LocalDate startDate;

    private LocalDate endDate;
    private String policyNumber;
    private boolean isMyPolicy;

    public static PolicyDto toPolicyDto(Policy policy) {
      PolicyDto policyDto = new PolicyDto();
      policyDto.setBranchCode(policy.getBranchCode());
      policyDto.setAmount(policy.getAmount());
      policyDto.setStartDate(policy.getStartDate());
      policyDto.setEndDate(policy.getEndDate());
      policyDto.setPolicyNumber(policy.getPolicyNumber());

    return policyDto;
    }

    public List<PolicyDto> toPolicyDtos(List<Policy> policies) {
        List<PolicyDto> policyDtos = new ArrayList<>();
        for (Policy policy : policies) {
            policyDtos.add(toPolicyDto(policy));
        }
    return policyDtos;
    }


}
