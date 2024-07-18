package com.adayazilim.sigorta.dto;

import com.adayazilim.sigorta.entity.Customer;
import lombok.Data;

@Data
public class CreateCustomerDto {

    private String identificationNumber;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String city;
    private String district;

    public static Customer toCustomer(CreateCustomerDto createCustomerDto) {
        Customer customer = new Customer();
        customer.setIdentificationNumber(createCustomerDto.getIdentificationNumber());
        customer.setName(createCustomerDto.getName());
        customer.setSurname(createCustomerDto.getSurname());
        customer.setEmail(createCustomerDto.getEmail());
        customer.setPhone(createCustomerDto.getPhone());
        customer.setCity(createCustomerDto.getCity());
        customer.setDistrict(createCustomerDto.getDistrict());
        return customer;

    }
}
