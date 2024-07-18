package com.adayazilim.sigorta.dto;

import com.adayazilim.sigorta.entity.Policy;
import lombok.Data;

@Data
public class CreatePolicyDto {

    private char status;
    private String branchCode;
    private double amount;

    public static Policy toEntity(CreatePolicyDto dto) {
        Policy policy = new Policy();
        policy.setAmount(dto.getAmount());
        policy.setBranchCode(dto.getBranchCode());
        policy.setStatus(dto.getStatus());
        return policy;
    }

}
