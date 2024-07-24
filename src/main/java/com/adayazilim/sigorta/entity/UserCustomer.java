//package com.adayazilim.sigorta.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "user_customer")
////@Data
////public class UserCustomer {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    @ManyToOne
////    @JoinColumn(name = "user_id", nullable = false)
////    private User user;
////
////    @ManyToOne
////    @JoinColumn(name = "customer_id", nullable = false)
////    private Customer customer;
////
////    @Column(name = "created_at")
////    private LocalDateTime createdAt = LocalDateTime.now();
////
////    @PostPersist
////    protected void prePersist() {
////
////        this.createdAt = LocalDateTime.now();
////    }
////
////
////}
////
////
