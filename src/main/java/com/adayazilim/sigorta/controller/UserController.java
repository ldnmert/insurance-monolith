package com.adayazilim.sigorta.controller;

import com.adayazilim.sigorta.entity.User;
import com.adayazilim.sigorta.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public List<User> getUsers() {}

}
