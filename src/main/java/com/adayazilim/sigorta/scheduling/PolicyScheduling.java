package com.adayazilim.sigorta.scheduling;

import com.adayazilim.sigorta.entity.Policy;
import com.adayazilim.sigorta.repository.PolicyRepository;
import com.adayazilim.sigorta.service.PolicyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PolicyScheduling {

    private final PolicyRepository policyRepository;

    public PolicyScheduling(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;

    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredPolicy() {
        List<Policy> policies = policyRepository.findByStatus('T');
        LocalDate fiveDaysFromNow = LocalDate.now().plusDays(5);

        for (Policy policy : policies) {
            if (policy.getEndDate() != null && policy.getEndDate().equals(fiveDaysFromNow)) {
                policyRepository.delete(policy);
            }
        }
    }


}
