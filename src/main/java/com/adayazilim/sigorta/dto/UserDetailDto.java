package com.adayazilim.sigorta.dto;

import com.adayazilim.sigorta.entity.Role;
import com.adayazilim.sigorta.entity.User;
import lombok.Data;
import com.adayazilim.sigorta.entity.Customer;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDetailDto {

    private Long id;
    private String username;
    private String password;
    private Role role;
    private List<Long> customerIds;
//    private List<String> customerNames;

    public static UserDetailDto toDto(User user) {
        UserDetailDto dto = new UserDetailDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());

        List<Long> customerIds = user.getCustomers().stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
        dto.setCustomerIds(customerIds);

//        List<String> customerNames = user.getCustomers().stream()
//                .map(Customer::getName)
//                .collect(Collectors.toList());
//        dto.setCustomerNames(customerNames);

        return dto;
    }


}
