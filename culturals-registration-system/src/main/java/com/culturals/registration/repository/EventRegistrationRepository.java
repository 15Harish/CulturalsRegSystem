package com.culturals.registration.repository;

import com.culturals.registration.domain.Event;
import com.culturals.registration.domain.EventRegistration;
import com.culturals.registration.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    
    List<EventRegistration> findByParticipant(Participant participant);
    
    List<EventRegistration> findByEvent(Event event);
    
    Optional<EventRegistration> findByParticipantAndEvent(Participant participant, Event event);
    
    boolean existsByParticipantAndEvent(Participant participant, Event event);
    
    int countByEvent(Event event);
}
