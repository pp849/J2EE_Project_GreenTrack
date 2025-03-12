package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.EnrolledEvent;
import com.luv2code.springboot.thymeleafdemo.model.Event;
import com.luv2code.springboot.thymeleafdemo.model.User;
import com.luv2code.springboot.thymeleafdemo.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Display sign-up form
    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("user", new User());
        return "user-signup";
    }

    // Process user sign-up
    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user, Model model) {
        userService.createUser(user);
        model.addAttribute("message", "User created successfully! Please login.");
        return "user-login";
    }

    // Display login form
    @GetMapping("/login")
    public String loginForm() {
        return "user-login";
    }

    // Process user login
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        Optional<User> user = userService.loginUser(username, password);
        if (user.isPresent()) {
            return "redirect:/user/home/" + user.get().getId();
        } else {
            model.addAttribute("error", "Invalid Credentials");
            return "user-login";
        }
    }

    // Display user home page
    @GetMapping("/home/{id}")
    public String userHome(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("userId", id);
        model.addAttribute("user", user); // Pass the user object
        model.addAttribute("message", "Login successful!");
        return "user-home";
    }

    @GetMapping("/{id}")
    public String getUserDetails(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user); // Pass the user object
        return "user-details";
    }

    // Display events page


    @GetMapping("/events/{id}")
    public String showEventsPage(@PathVariable Long id, Model model) {
        List<Event> availableEvents = userService.getAllEvents();
        List<EnrolledEvent> enrolledEvents = userService.getEnrolledEvents(id);

        model.addAttribute("userId", id);
        model.addAttribute("availableEvents", availableEvents); // Ensure this is correctly passed
        model.addAttribute("enrolledEvents", enrolledEvents); // Ensure this is correctly passed
        return "user-events";
    }

    // Show form for updating user information
    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    // Process update of user information
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute User updatedUser,
                             Model model) {
        User savedUser = userService.updateUserInfo(id, updatedUser);
        model.addAttribute("user", savedUser);
        model.addAttribute("message", "Information updated successfully");
        return "user-details";
    }

    // Enroll in an event
    @PostMapping("/enroll/{userId}/{eventId}")
    public String enrollEvent(@PathVariable Long userId,
                              @PathVariable Long eventId) {
        userService.enrollInEvent(userId, eventId);
        return "redirect:/user/events/" + userId;
    }

    // Drop an event
    @PostMapping("/drop/{userId}/{eventId}")
    public String dropEvent(@PathVariable Long userId,
                            @PathVariable Long eventId) {
        userService.dropEvent(userId, eventId);
        return "redirect:/user/events/" + userId;
    }


//    @GetMapping("/leaderboard")
//    public String showLeaderboard(Model model) {
//        List<User> users = userService.getAllUsersSortedByRewardPointsDesc();
//        model.addAttribute("users", users);
//        return "leaderboard"; // Thymeleaf template for the leaderboard
//    }

    @GetMapping("/leaderboard")
    public String showLeaderboard(@RequestParam Long userId, Model model) {
        List<User> users = userService.getAllUsersSortedByRewardPointsDesc();
        model.addAttribute("users", users);
        model.addAttribute("userId", userId); // Pass the userId to the template
        return "leaderboard";
    }

    // Logout user
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return "redirect:/user/login"; // Redirect to the login page
    }

}