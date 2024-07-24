package com.adayazilim.sigorta.service;

import com.adayazilim.sigorta.dto.PolicyDetailDto;
import com.adayazilim.sigorta.entity.Customer;
import com.adayazilim.sigorta.entity.Policy;
import com.adayazilim.sigorta.entity.User;
import com.adayazilim.sigorta.repository.PolicyRepository;

import com.adayazilim.sigorta.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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

    public double changeStatusAndGetAmount(String policyNumber) {

        Policy policy = policyRepository.findByPolicyNumber(policyNumber).orElseThrow(NoSuchElementException::new);
        policy.setStatus('K');
        return policy.getAmount();
    }

    public String generateUniquePolicyNumber() {
        Random random = new Random();
        String policyNumber;
        do {
            policyNumber = String.format("%08d", random.nextInt(100000000));
        } while (policyRepository.existsByPolicyNumber(policyNumber));
        return policyNumber;
    }

    public List<PolicyDetailDto> getLastTenPolicyOfCurrentUser(Long id) {

        List<Policy> policies =  policyRepository.findTop10ByUserIdOrderByCreatedAtDesc(id);



        return PolicyDetailDto.toDtoList(policies);

    }

    public List<PolicyDetailDto> getPolicyOfCurrentUserByCustomer(Long userId, Long customerId) {
        List<Policy> policies = policyRepository.findByCustomerIdAndUserId(customerId, userId);
        return PolicyDetailDto.toDtoList(policies);
    }

    public List<PolicyDetailDto> getLast20Policies() {
        return PolicyDetailDto.toDtoList(policyRepository.findTop10ByOrderByCreatedAtDesc());
    }

    public PolicyDetailDto getPolicyDetailByPolicyNumber(String policyNumber) {
        return PolicyDetailDto.toDto(policyRepository.findByPolicyNumber(policyNumber).get());
    }

    public Policy getPolicyByPolicyNumber(String policyNumber) {
        return policyRepository.findByPolicyNumber(policyNumber).get();
    }

    public List<PolicyDetailDto> getPoliciesOfCurrentUser(Long id) {
        return PolicyDetailDto.toDtoList(policyRepository.findByUserId(id));
    }

    public List<PolicyDetailDto> getPoliciesByUserIdAndStatusAndSort(Long id, char status, String sortOption) {

        Sort sort = Sort.by("createdAt").descending(); // Default sort by createdAt descending

        if (sortOption != null) {
            switch (sortOption) {
                case "recent":
                    sort = Sort.by("createdAt").descending();
                    break;
                case "amountAsc":
                    sort = Sort.by("amount").ascending();
                    break;
                case "amountDesc":
                    sort = Sort.by("amount").descending();
                    break;
            }
        }

        if(status == 'H') {
            return PolicyDetailDto.toDtoList(policyRepository.findByUserId(id, sort));
        }
        else {
            return PolicyDetailDto.toDtoList(policyRepository.findByUserIdAndStatus(id, status, sort));
        }
    }
}
