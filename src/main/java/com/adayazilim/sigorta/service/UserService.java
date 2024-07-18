package com.adayazilim.sigorta.service;

import com.adayazilim.sigorta.entity.User;
import com.adayazilim.sigorta.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

}
