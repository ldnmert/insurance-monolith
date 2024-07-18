package com.adayazilim.sigorta.controller;

import com.adayazilim.sigorta.dto.CreatePolicyDto;
import com.adayazilim.sigorta.entity.Policy;
import com.adayazilim.sigorta.entity.User;
import com.adayazilim.sigorta.service.CustomerService;
import com.adayazilim.sigorta.service.PolicyService;
import com.adayazilim.sigorta.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Boolean> createPolicy(@RequestBody CreatePolicyDto policyDto, Authentication authentication
            , @RequestParam Long customerId) {

    User user = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);
    Policy policy = CreatePolicyDto.toEntity(policyDto);
    policy.setUser(user);
    policy.setPolicyNo("1234443");
    policy.setCustomer(customerService.getCustomerById(customerId));
    policyService.createPolicy(policy);
    return ResponseEntity.ok(true);
    }
}
