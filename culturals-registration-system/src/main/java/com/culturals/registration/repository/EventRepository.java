package com.culturals.registration.repository;

import com.culturals.registration.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    
    List<Event> findByIsVisible(Boolean isVisible);
    
    List<Event> findByRegistrationDeadlineAfter(LocalDateTime date);
    
    List<Event> findByIsVisibleAndRegistrationDeadlineAfter(Boolean isVisible, LocalDateTime date);
}
