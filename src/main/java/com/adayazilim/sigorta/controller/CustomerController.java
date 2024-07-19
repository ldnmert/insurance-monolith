package com.adayazilim.sigorta.controller;

import com.adayazilim.sigorta.dto.CreateCustomerDto;
import com.adayazilim.sigorta.dto.CustomerDetailDto;
import com.adayazilim.sigorta.entity.Customer;
import com.adayazilim.sigorta.entity.User;
import com.adayazilim.sigorta.service.CustomerService;
import com.adayazilim.sigorta.service.UserService;
import com.adayazilim.sigorta.util.AuthenticationUtil;
import lombok.Getter;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final UserService userService;

    public CustomerController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Boolean> createCustomer(@RequestBody CreateCustomerDto createCustomerDto, Authentication authentication) {

       User currentUser = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);
        Customer customerExist = customerService.getCustomerByIdentificationNumber(createCustomerDto.getIdentificationNumber());
        if(customerExist != null){
            customerExist.getUsers().add(currentUser);
            customerService.createCustomer(customerExist);
            return ResponseEntity.ok(true);
        }

       Customer customer = CreateCustomerDto.toCustomer(createCustomerDto);
       customer.getUsers().add(currentUser);

       customerService.createCustomer(customer);

        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDetailDto>> getCustomers(Authentication authentication) {
        User currentUser = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);
        List<CustomerDetailDto> customers = customerService.getCustomersOfCurrentUser(currentUser.getId());
        return ResponseEntity.ok(customers);
    }




}
