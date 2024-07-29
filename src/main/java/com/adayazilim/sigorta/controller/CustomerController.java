package com.adayazilim.sigorta.controller;

import com.adayazilim.sigorta.dto.CreateCustomerDto;
import com.adayazilim.sigorta.dto.CustomerDetailDto;
import com.adayazilim.sigorta.entity.Customer;
import com.adayazilim.sigorta.entity.User;
import com.adayazilim.sigorta.service.CustomerService;
import com.adayazilim.sigorta.service.PolicyService;
import com.adayazilim.sigorta.service.UserService;
import com.adayazilim.sigorta.util.AuthenticationUtil;
import lombok.Getter;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
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
    private final PolicyService policyService;

    public CustomerController(CustomerService customerService, UserService userService, PolicyService policyService) {
        this.customerService = customerService;
        this.userService = userService;
        this.policyService = policyService;
    }

//    @PostMapping
//    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerDto createCustomerDto, Authentication authentication) {
//
//        User currentUser = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);
//        Customer customerExist = customerService.getCustomerByIdentificationNumber(createCustomerDto.getIdentificationNumber());
//        if (customerExist != null && customerExist.getUsers().contains(currentUser)) {
////            customerExist.getUsers().add(currentUser);
////            customerService.createCustomer(customerExist);
//            return ResponseEntity.ok("Bu kişi zaten bu kullanıcının müşterisi");
//        } else if (customerExist != null && !customerExist.getUsers().contains(currentUser)) {
//            customerExist.getUsers().add(currentUser);
//            customerService.createCustomer(customerExist);
//            return ResponseEntity.ok("Müşteriye bu kullanıcı eklendi");
//        } else {
//            Customer customer = CreateCustomerDto.toCustomer(createCustomerDto);
//            customer.getUsers().add(currentUser);
//
//            customerService.createCustomer(customer);
//
//            return ResponseEntity.ok("Yeni müşteri eklendi");
//        }
//    }

    @PostMapping
    public ResponseEntity<CustomerDetailDto> createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        Customer customer = CreateCustomerDto.toCustomer(createCustomerDto);
        CustomerDetailDto customerDetailDto = customerService.createCustomer(customer);
        return ResponseEntity.ok(customerDetailDto);
    }

    @GetMapping("/check-customer-exist")
    public ResponseEntity<CustomerDetailDto> checkCustomerExist(@RequestParam String identificationNumber) {
        Customer customer = customerService.getCustomerByIdentificationNumber(identificationNumber);

        if (customer == null)
            return ResponseEntity.notFound().build();

        else
            return ResponseEntity.ok().body(CustomerDetailDto.toDto(customer));


    }

//    @GetMapping
//    public ResponseEntity<List<CustomerDetailDto>> getCustomers(Authentication authentication) {
//        User currentUser = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);
//        List<CustomerDetailDto> customers = customerService.getCustomersOfCurrentUser(currentUser.getId());
//        return ResponseEntity.ok(customers);
//    }


//    @GetMapping("/last-ten-customers")
//    public ResponseEntity<List<CustomerDetailDto>> getLastTenCustomersOfCurrentUser(Authentication authentication) {
//
//        User currentUser = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);
//
//        List<CustomerDetailDto> customers = customerService.getLastTenCustomersByUserId(currentUser.getId());
//
//        return ResponseEntity.ok(customers);
//    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDetailDto>> getCustomersByUserId(Authentication authentication) {
        User currentUser = userService.getUserByName(authentication.getName()).orElseThrow(NoSuchElementException::new);
        List<CustomerDetailDto> customers = CustomerDetailDto.toDtoList(policyService.getCustomersByUserId(currentUser.getId()));
        return ResponseEntity.ok(customers);
    }


}
