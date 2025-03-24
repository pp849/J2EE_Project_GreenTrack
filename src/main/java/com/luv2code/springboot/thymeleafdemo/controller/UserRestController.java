package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Admin;
import com.luv2code.springboot.thymeleafdemo.model.Event;
import com.luv2code.springboot.thymeleafdemo.model.User;
import com.luv2code.springboot.thymeleafdemo.services.AdminService;
import com.luv2code.springboot.thymeleafdemo.services.EventService;
import com.luv2code.springboot.thymeleafdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.loginUser(username, password);
        return user.isPresent() ? ResponseEntity.ok("Login successful") : ResponseEntity.status(401).body("Invalid Credentials");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        userService.updateUserInfo(id, updatedUser);
        return ResponseEntity.ok("User updated successfully");
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<User>> getLeaderboard() {
        return ResponseEntity.ok(userService.getAllUsersSortedByRewardPointsDesc());
    }
}

