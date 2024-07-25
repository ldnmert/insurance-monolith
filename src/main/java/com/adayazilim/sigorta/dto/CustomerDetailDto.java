package com.adayazilim.sigorta.dto;

import com.adayazilim.sigorta.entity.Customer;
import lombok.Data;
import  com.adayazilim.sigorta.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CustomerDetailDto {

    private Long id;

    private String identificationNumber;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String city;
    private String district;
//    private List<Long> userIds;
//    private List<String> userNames;

    public static CustomerDetailDto toDto(Customer customer) {
        CustomerDetailDto dto = new CustomerDetailDto();
        dto.setIdentificationNumber(customer.getIdentificationNumber());
        dto.setName(customer.getName());
        dto.setSurname(customer.getSurname());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setCity(customer.getCity());
        dto.setDistrict(customer.getDistrict());
        dto.setId(customer.getId());
//        List<Long> userIds = customer.getUsers().stream()
//                .map(User::getId)
//                .collect(Collectors.toList());
//        dto.setUserIds(userIds);
//
//        dto.setUserIds(userIds);



    return dto;

    }

    public static List<CustomerDetailDto> toDtoList(List<Customer> customerList) {
        List<CustomerDetailDto> dtoList = new ArrayList<>();
        for (Customer customer : customerList) {
            toDto(customer);
            dtoList.add(toDto(customer));
        }
        return dtoList;
    }

}
