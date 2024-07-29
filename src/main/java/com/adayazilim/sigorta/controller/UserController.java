package com.adayazilim.sigorta.controller;

import com.adayazilim.sigorta.dto.UserDetailDto;
import com.adayazilim.sigorta.dto.UserInfoDto;
import com.adayazilim.sigorta.entity.User;
import com.adayazilim.sigorta.repository.UserRepository;
import com.adayazilim.sigorta.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("/details")
    public ResponseEntity<UserInfoDto> getUserDetails(Authentication authentication) {

        User user = userRepository.findByUsername(authentication.getName()).get();
        return ResponseEntity.ok(UserInfoDto.toDto(user));

    }

//    @GetMapping
//    public List<User> getUsers() {}

}
