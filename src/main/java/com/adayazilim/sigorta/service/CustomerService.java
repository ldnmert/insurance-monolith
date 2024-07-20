package com.adayazilim.sigorta.service;

import com.adayazilim.sigorta.dto.CustomerDetailDto;
import com.adayazilim.sigorta.entity.Customer;
import com.adayazilim.sigorta.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<CustomerDetailDto> getCustomersOfCurrentUser(Long id) {
        List<Customer> customers = customerRepository.findByUsersId(id);

        return CustomerDetailDto.toDtoList(customers);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }

    public Customer getCustomerByIdentificationNumber(String identificationNumber) {
        return customerRepository.findByIdentificationNumber(identificationNumber)
                .orElse(null);
    }


    public List<CustomerDetailDto> getLastTenCustomersByUserId(Long id) {

        return CustomerDetailDto.toDtoList(customerRepository.findTop10ByUsersIdOrderByCreatedAtDesc(id));

    }
}
