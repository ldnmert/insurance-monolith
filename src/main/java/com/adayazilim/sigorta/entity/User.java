package com.adayazilim.sigorta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String photo;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @ManyToMany(mappedBy = "users")
//    private List<Customer> customers = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Policy> policies = new ArrayList<>();

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
