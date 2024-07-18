package com.adayazilim.sigorta.service;

import com.adayazilim.sigorta.entity.Policy;
import com.adayazilim.sigorta.repository.PolicyRepository;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }


    public void createPolicy(Policy policy) {
        policyRepository.save(policy);
    }
}
