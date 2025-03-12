package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Admin;
import com.luv2code.springboot.thymeleafdemo.model.Event;
import com.luv2code.springboot.thymeleafdemo.services.AdminService;
import com.luv2code.springboot.thymeleafdemo.services.EventService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EventService eventService;

    // Display admin login form
    @GetMapping("/login")
    public String loginForm() {
        return "admin-login";
    }

    // Process admin login
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        Optional<Admin> admin = adminService.loginAdmin(username, password);
        if (admin.isPresent()) {
            return "redirect:/admin/dashboard"; // Redirect to the admin dashboard
        } else {
            model.addAttribute("error", "Invalid Credentials");
            return "admin-login";
        }
    }

    // Display admin dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "admin-dashboard";
    }

    // Display event creation form
    @GetMapping("/events/add")
    public String showAddEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "add-event";
    }

    // Process event creation form submission
    @PostMapping("/events/add")
    public String addEvent(@ModelAttribute Event event, Model model) {
        eventService.createEvent(event); // Save the new event
        model.addAttribute("message", "Event added successfully!");
        return "redirect:/admin/dashboard"; // Redirect to the admin dashboard
    }

    // Display event update form
    @GetMapping("/events/update/{id}")
    public String showUpdateEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "update-event";
    }

    // Process event update form submission
    @PostMapping("/events/update/{id}")
    public String updateEvent(@PathVariable Long id,
                              @ModelAttribute Event updatedEvent,
                              Model model) {
        eventService.updateEvent(id, updatedEvent);
        model.addAttribute("message", "Event updated successfully!");
        return "redirect:/admin/dashboard"; // Redirect to the admin dashboard
    }

    // Delete an event
    @GetMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable Long id, Model model) {
        eventService.deleteEvent(id);
        model.addAttribute("message", "Event deleted successfully!");
        return "redirect:/admin/dashboard"; // Redirect to the admin dashboard
    }

    // Logout admin
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return "redirect:/admin/login"; // Redirect to the admin login page
    }
}