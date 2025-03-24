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
@RequestMapping("/api/events")
public class EventRestController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollForEvent(@RequestParam Long userId, @RequestParam Long eventId) {
        eventService.enrollForEvent(eventId);
        return ResponseEntity.ok("Enrolled successfully");
    }
}

