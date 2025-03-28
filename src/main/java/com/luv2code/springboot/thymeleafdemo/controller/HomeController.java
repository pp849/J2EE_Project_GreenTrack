package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Activity;
import com.luv2code.springboot.thymeleafdemo.model.Event;
import com.luv2code.springboot.thymeleafdemo.model.User;
import com.luv2code.springboot.thymeleafdemo.services.ActivityService;
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
    @Autowired
    private ActivityService activityService;

    // Home Page
    @GetMapping("/")
    public String homePage() {
        return "home";
    }



    @GetMapping("/activitiesPage")  // Change the URL to avoid conflict
    public String personalactivitiesPage(Model model) {
        List<Activity> activities = activityService.getAllActivities();
        model.addAttribute("activities", activityService.getAllActivities());
        return "activities"; // Return the corresponding view name
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
