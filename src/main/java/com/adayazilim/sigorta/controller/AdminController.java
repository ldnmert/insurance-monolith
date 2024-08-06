package com.adayazilim.sigorta.controller;

import com.adayazilim.sigorta.dto.CustomerDetailDto;
import com.adayazilim.sigorta.dto.PolicyDetailDto;
import com.adayazilim.sigorta.entity.Customer;
import com.adayazilim.sigorta.repository.CustomerRepository;
import com.adayazilim.sigorta.repository.PolicyRepository;
import com.adayazilim.sigorta.service.CustomerService;
import com.adayazilim.sigorta.service.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final CustomerService customerService;
    private final PolicyService policyService;
    private final PolicyRepository policyRepository;
    private final CustomerRepository customerRepository;

    public AdminController(CustomerService customerService, PolicyService policyService, PolicyRepository policyRepository, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.policyService = policyService;
        this.policyRepository = policyRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/last-20-customer")
    public ResponseEntity<List<CustomerDetailDto>> getLast20CustomerForAdmin(){
        return ResponseEntity.ok(customerService.getLastTenCustomerForAdmin());
    }
    @GetMapping("/last-20-policies")
    public ResponseEntity<List<PolicyDetailDto>> getLast20PoliciesForAdmin(){

        return ResponseEntity.ok(policyService.getLast20Policies());

    }

    @GetMapping("/get-policy/{policyNumber}")
    public ResponseEntity<PolicyDetailDto> getPolicyByPolicyNumber(@PathVariable  String policyNumber){
        return ResponseEntity.ok(policyService.getPolicyDetailByPolicyNumber(policyNumber));
    }

    @GetMapping("/get-customer/{customerNumber}")
    public ResponseEntity<CustomerDetailDto> getCustomerByIdentificationNumber(@PathVariable String customerNumber){
        return ResponseEntity.ok().body(CustomerDetailDto.toDto(customerService.getCustomerByIdentificationNumber(customerNumber)));
    }

    @GetMapping("/get-policies")
    public ResponseEntity<List<PolicyDetailDto>> getPolicies(@RequestParam String sortOption, @RequestParam char status){
        return ResponseEntity.ok(policyService.getPoliciesByStatusAndSort(status, sortOption));
    }

    @GetMapping("/expiringPolicies")
    public ResponseEntity<List<PolicyDetailDto>> getExpiringPolicies(){
        return ResponseEntity.ok(policyService.getExpiringPoliciesAdmin());
    }

    @GetMapping("/ratio")
    public ResponseEntity<Double> getRatioK(){
        return ResponseEntity.ok(policyService.getStatusKRatio());
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDetailDto>> getCustomers(){
        return ResponseEntity.ok(CustomerDetailDto.toDtoList(customerRepository.findAll()));
    }
}
