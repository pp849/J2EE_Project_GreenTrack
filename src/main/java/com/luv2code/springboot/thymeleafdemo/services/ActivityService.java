package com.luv2code.springboot.thymeleafdemo.services;

import com.luv2code.springboot.thymeleafdemo.model.Activity;
import com.luv2code.springboot.thymeleafdemo.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
}
