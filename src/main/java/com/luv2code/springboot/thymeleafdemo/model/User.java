package com.luv2code.springboot.thymeleafdemo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String name;
    private String email;
    private String address;

    @Column(name = "total_reward_points", nullable = false, columnDefinition = "int default 0")
    private int totalRewardPoints = 0; // Add this field
}