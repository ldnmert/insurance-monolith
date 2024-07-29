package com.adayazilim.sigorta.dto;

import com.adayazilim.sigorta.entity.User;
import lombok.Data;

@Data
public class UserInfoDto {




    private String firstName;
    private String lastName;
    private String photo;

    public static UserInfoDto toDto(User user){
        UserInfoDto dto = new UserInfoDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoto(user.getPhoto());
        return dto;
    }


}
