package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Event;
import com.luv2code.springboot.thymeleafdemo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public String viewEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events"; // Returns the view named 'events.html'
    }

    @PostMapping("/enroll")
    public String enrollForEvent(@RequestParam Long eventId, @RequestParam Long userId) {
        eventService.enrollForEvent(eventId, userId);
        return "redirect:/events"; // Redirect back to events page after enrollment
    }

    // Create a new event (Ensure this is POST and the data is passed)
    @PostMapping("/admin/events/new")
    public String createEvent(@ModelAttribute Event event) {
        eventService.createEvent(event); // Save the event
        return "redirect:/events"; // Redirect back to the events page after creating an event
    }

    // Get event by ID (Retrieve event details by ID)
    @GetMapping("/events/{id}")
    public String getEventById(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "event-detail"; // Display event details in 'event-detail.html'
    }

    // Update an existing event (Ensure it's mapped to handle POST requests)
    @PostMapping("/admin/events/{id}/update")
    public String updateEvent(@PathVariable Long id, @ModelAttribute Event updatedEvent) {
        eventService.updateEvent(id, updatedEvent); // Update event data
        return "redirect:/events"; // Redirect back to the events page after updating
    }

    // Delete an event
    @PostMapping("/admin/events/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id); // Delete the event by its ID
        return "redirect:/events"; // Redirect back to the events page after deletion
    }
}
