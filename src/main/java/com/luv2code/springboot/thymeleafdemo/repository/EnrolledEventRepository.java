package com.luv2code.springboot.thymeleafdemo.repository;


import com.luv2code.springboot.thymeleafdemo.model.EnrolledEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnrolledEventRepository extends JpaRepository<EnrolledEvent, Long> {
    List<EnrolledEvent> findByUserId(Long userId);
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
    void deleteByUserIdAndEventId(Long userId, Long eventId);
}