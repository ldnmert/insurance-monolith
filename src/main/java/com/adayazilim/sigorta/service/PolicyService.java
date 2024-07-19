package com.adayazilim.sigorta.service;

import com.adayazilim.sigorta.entity.Customer;
import com.adayazilim.sigorta.entity.Policy;
import com.adayazilim.sigorta.entity.User;
import com.adayazilim.sigorta.repository.PolicyRepository;

import com.adayazilim.sigorta.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;
    private final CustomerService customerService;

    public PolicyService(PolicyRepository policyRepository, UserRepository userRepository, CustomerService customerService) {
        this.policyRepository = policyRepository;
        this.userRepository = userRepository;
        this.customerService = customerService;
    }


    public String createPolicy(String branchCode, Long customerId, double amount) {
        Policy policy = new Policy();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.getCustomerById(customerId);
        policy.setCustomer(customer);
        policy.setBranchCode(branchCode);
        String policyNumber = generateUniquePolicyNumber();
         policy.setPolicyNumber(policyNumber);

        policy.setAmount(amount);
        User currentUser = userRepository.findByUsername(authentication.getName()).orElseThrow(NoSuchElementException::new);
        policy.setUser(currentUser);



        policyRepository.save(policy);

        return policyNumber;
    }


    public String generateUniquePolicyNumber() {
        Random random = new Random();
        String policyNumber;
        do {
            policyNumber = String.format("%08d", random.nextInt(100000000));
        } while (policyRepository.existsByPolicyNumber(policyNumber));
        return policyNumber;
    }
}
