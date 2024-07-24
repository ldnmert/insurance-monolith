package com.adayazilim.sigorta.controller;

import com.adayazilim.sigorta.dto.CreatePaymentDto;
import com.adayazilim.sigorta.dto.CreatePolicyDto;
import com.adayazilim.sigorta.dto.PolicyDetailDto;
import com.adayazilim.sigorta.entity.Policy;
import com.adayazilim.sigorta.entity.User;
import com.adayazilim.sigorta.service.CustomerService;
import com.adayazilim.sigorta.service.PolicyService;
import com.adayazilim.sigorta.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    private final PolicyService policyService;
    private final UserService userService;
    private final CustomerService customerService;


    public PolicyController(PolicyService policyService, UserService userService, CustomerService customerService) {
        this.policyService = policyService;
        this.userService = userService;
        this.customerService = customerService;

    }

//    @PostMapping
//    public ResponseEntity<Boolean> createPolicy(@RequestBody CreatePolicyDto policyDto, Authentication authentication
//            , @RequestParam Long customerId) {
//
//    User user = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);
//    Policy policy = CreatePolicyDto.toEntity(policyDto);
//    policy.setUser(user);
////    policy.setPolicyNo("1234443");
//    policy.setCustomer(customerService.getCustomerById(customerId));
//    policyService.createPolicy(policy);
//    return ResponseEntity.ok(true);
//    }

    @GetMapping("/last-ten-policy")
    public ResponseEntity<List<PolicyDetailDto>> getLastTenPolicy(Authentication authentication) {

        User currentUser = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);

        List<PolicyDetailDto> policies = policyService.getLastTenPolicyOfCurrentUser(currentUser.getId());
        return ResponseEntity.ok(policies);
    }

    @GetMapping("/policies-of-user")
    public ResponseEntity<List<PolicyDetailDto>> getPoliciesOfUser(Authentication authentication) {
        User currentUser = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);
        List<PolicyDetailDto> policiesDetailDtos = policyService.getPoliciesOfCurrentUser(currentUser.getId());
        return ResponseEntity.ok(policiesDetailDtos);
    }

    @GetMapping("/get-policies-of-customer")
    public ResponseEntity<List<PolicyDetailDto>> getPoliciesOfCustomerOfcurrentUser(Authentication authentication, @RequestParam Long customerId) {
        User currentUser = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);

        return  ResponseEntity.ok(policyService.getPolicyOfCurrentUserByCustomer(currentUser.getId(), customerId));

    }

    @GetMapping("/search-policy-number")
    public ResponseEntity<PolicyDetailDto> getPolicyOfCurrentUser(Authentication authentication, @RequestParam String policyNumber) {
        System.out.println("qww");
        User currentUser = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);
        Policy policy = policyService.getPolicyByPolicyNumber(policyNumber);

        if(policy.getUser().getId() == currentUser.getId()) {
            return ResponseEntity.ok(PolicyDetailDto.toDto(policy));
        }

        else
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
    }

    @GetMapping("/filter-and-sort")
    public ResponseEntity<List<PolicyDetailDto>> getFilterByStatus(@RequestParam char status, Authentication authentication, @RequestParam String sortOption) {
        User currentUser = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);
        return ResponseEntity.ok(policyService.getPoliciesByUserIdAndStatusAndSort(currentUser.getId(), status, sortOption));

    }


}
