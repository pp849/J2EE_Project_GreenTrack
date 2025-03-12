package com.luv2code.springboot.thymeleafdemo.controller;


import com.luv2code.springboot.thymeleafdemo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public String viewEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events";
    }

    @PostMapping("/enroll")
    public String enrollForEvent(@RequestParam Long eventId) {
        eventService.enrollForEvent(eventId);
        return "redirect:/events";
    }
}