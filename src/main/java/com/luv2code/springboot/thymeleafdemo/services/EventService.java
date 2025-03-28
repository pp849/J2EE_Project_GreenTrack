package com.luv2code.springboot.thymeleafdemo.services;

import com.luv2code.springboot.thymeleafdemo.model.EnrolledEvent;
import com.luv2code.springboot.thymeleafdemo.model.Event;
import com.luv2code.springboot.thymeleafdemo.model.User;
import com.luv2code.springboot.thymeleafdemo.repository.EnrolledEventRepository;
import com.luv2code.springboot.thymeleafdemo.repository.EventRepository;
import com.luv2code.springboot.thymeleafdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnrolledEventRepository enrolledEventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void updateEvent(Long id, Event updatedEvent) {
        Event existingEvent = getEventById(id);
        existingEvent.setEventName(updatedEvent.getEventName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setReward(updatedEvent.getReward());
        existingEvent.setEventDate(updatedEvent.getEventDate());
        existingEvent.setLocation(updatedEvent.getLocation());
        eventRepository.save(existingEvent);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public void enrollForEvent(Long eventId, Long userId) {
        Event event = getEventById(eventId);  // Get the event by its ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a new EnrolledEvent and associate user with the event
        EnrolledEvent enrolledEvent = new EnrolledEvent();
        enrolledEvent.setEvent(event);  // Set the event
        enrolledEvent.setUser(user);    // Set the user


        // Save the EnrolledEvent
        enrolledEventRepository.save(enrolledEvent);
    }

    // Enroll user for an event (store event date in EnrolledEvent)

}
