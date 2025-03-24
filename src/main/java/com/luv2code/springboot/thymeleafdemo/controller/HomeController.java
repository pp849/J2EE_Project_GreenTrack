package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Event;
import com.luv2code.springboot.thymeleafdemo.model.User;
import com.luv2code.springboot.thymeleafdemo.services.EventService;
import com.luv2code.springboot.thymeleafdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {


    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    // Home Page
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    // Activities Page
    @GetMapping("/activities")
    public String activitiesPage() {
        return "activities";
    }

    // Leaderboard Page
    @GetMapping("/rank")
    public String leaderboardPage(Model model) {
            List<User> leaderboardUsers = userService.getAllUsersSortedByRewardPointsDesc();
            model.addAttribute("leaderboardUsers", leaderboardUsers);
            return "rank";
    }

    // Events Page
    @GetMapping("/event")
    public String eventsPage(Model model) {
        List<Event> eventList = eventService.getAllEvents();
        model.addAttribute("events", eventList);
        return "event";
    }

    // Contact Page
    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }
}
