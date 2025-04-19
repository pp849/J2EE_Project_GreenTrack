package com.luv2code.springboot.thymeleafdemo.services;

import com.luv2code.springboot.thymeleafdemo.model.EnrolledEvent;
import com.luv2code.springboot.thymeleafdemo.model.Event;
import com.luv2code.springboot.thymeleafdemo.model.User;
import com.luv2code.springboot.thymeleafdemo.repository.EnrolledEventRepository;
import com.luv2code.springboot.thymeleafdemo.repository.EventRepository;
import com.luv2code.springboot.thymeleafdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EnrolledEventRepository enrolledEventRepository;

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Authenticate user login
    public Optional<User> loginUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    // Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update user's personal information
    public User updateUserInfo(Long userId, User updatedUser) {
        User existingUser = getUserById(userId);
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAddress(updatedUser.getAddress());
        return userRepository.save(existingUser);
    }

    // Get all available events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get enrolled events for a user
    public List<EnrolledEvent> getEnrolledEvents(Long userId) {
        return enrolledEventRepository.findByUserId(userId);
    }

    // Enroll in an event
    @Transactional
    public void enrollInEvent(Long userId, Long eventId) {
        User user = getUserById(userId);
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            if (!enrolledEventRepository.existsByUserIdAndEventId(userId, eventId)) {
                EnrolledEvent enrolledEvent = new EnrolledEvent();
                enrolledEvent.setUser(user);
                enrolledEvent.setEvent(event);
                enrolledEventRepository.save(enrolledEvent);

                // Add the event's reward to the user's total reward points
                user.setTotalRewardPoints(user.getTotalRewardPoints() + event.getReward());
                userRepository.save(user); // Save the updated user
            }
        } else {
            throw new RuntimeException("Event not found");
        }
    }

    // Drop an event
    @Transactional
    public void dropEvent(Long userId, Long eventId) {
        User user = getUserById(userId);
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();

            if (enrolledEventRepository.existsByUserIdAndEventId(userId, eventId)) {
                // Subtract the event's reward from the user's total reward points
                user.setTotalRewardPoints(user.getTotalRewardPoints() - event.getReward());
                userRepository.save(user); // Save the updated user

                // Delete the enrollment record
                enrolledEventRepository.deleteByUserIdAndEventId(userId, eventId);
            }
        } else {
            throw new RuntimeException("Event not found");
        }
    }

    public List<User> getAllUsersSortedByRewardPointsDesc() {
        return userRepository.findAllByOrderByTotalRewardPointsDesc();
    }
}
