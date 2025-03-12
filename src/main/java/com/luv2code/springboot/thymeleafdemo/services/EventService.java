package com.luv2code.springboot.thymeleafdemo.services;

import com.luv2code.springboot.thymeleafdemo.model.Event;
import com.luv2code.springboot.thymeleafdemo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;


    public void enrollForEvent(Long eventId) {
        // Logic to handle event enrollment
        System.out.println("User enrolled for event with ID: " + eventId);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }


    // Create a new event
    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    // Get event by ID
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    // Update an existing event
    public void updateEvent(Long id, Event updatedEvent) {
        Event existingEvent = getEventById(id);
        existingEvent.setEventName(updatedEvent.getEventName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setReward(updatedEvent.getReward());
        eventRepository.save(existingEvent);
    }

    // Delete an event
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}