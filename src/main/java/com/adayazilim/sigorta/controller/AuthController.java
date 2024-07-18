package com.adayazilim.sigorta.controller;

import com.adayazilim.sigorta.entity.User;
import com.adayazilim.sigorta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/sign-up")
    public ResponseEntity<Boolean> signUp(@RequestBody User userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userService.createUser(userDto);
        return ResponseEntity.ok(true);
    }
}