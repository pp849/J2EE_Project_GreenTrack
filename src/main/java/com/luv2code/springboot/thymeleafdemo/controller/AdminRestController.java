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
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EventService eventService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        Optional<Admin> admin = adminService.loginAdmin(username, password);
        return admin.isPresent() ? ResponseEntity.ok("Login successful") : ResponseEntity.status(401).body("Invalid Credentials");
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PostMapping("/events")
    public ResponseEntity<String> addEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return ResponseEntity.ok("Event added successfully");
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        eventService.updateEvent(id, event);
        return ResponseEntity.ok("Event updated successfully");
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully");
    }
}

