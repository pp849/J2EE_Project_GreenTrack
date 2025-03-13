package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // This will map the root URL to home.html
    public String showHomePage() {
        return "home"; // Return the home.html Thymeleaf template
    }
}

