package com.luv2code.springboot.thymeleafdemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data  // ✅ Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_name", nullable = false, unique = true)
    private String eventName;

    @Column(name = "description")
    private String description;

    @Column(name = "reward")
    private int reward;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;  // ✅ Automatically generates getEventDate() & setEventDate()

    @Column(name = "location", nullable = false)
    private String location;  // ✅ Automatically generates getLocation() & setLocation()

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnrolledEvent> enrolledEvents;
}
