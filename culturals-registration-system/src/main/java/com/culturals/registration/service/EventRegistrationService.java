package com.culturals.registration.service;

import com.culturals.registration.domain.Event;
import com.culturals.registration.domain.EventRegistration;
import com.culturals.registration.domain.Participant;
import com.culturals.registration.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventRegistrationService {
    
    @Autowired
    private EventRegistrationRepository registrationRepository;
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private EmailService emailService;
    
    @Transactional
    public EventRegistration registerForEvent(Participant participant, Event event, String specialRequirements) {
        // Check if already registered
        if (registrationRepository.existsByParticipantAndEvent(participant, event)) {
            throw new RuntimeException("Already registered for this event");
        }
        
        // Check if event is full
        if (event.isFull()) {
            throw new RuntimeException("Event is full");
        }
        
        // Check if registration deadline has passed
        if (LocalDateTime.now().isAfter(event.getRegistrationDeadline())) {
            throw new RuntimeException("Registration deadline has passed");
        }
        
        // Create registration
        EventRegistration registration = new EventRegistration(participant, event, specialRequirements);
        registration.setConfirmationCode(generateConfirmationCode());
        registration.setConfirmed(true);
        
        EventRegistration savedRegistration = registrationRepository.save(registration);
        
        // Increment event registered count
        eventService.incrementRegisteredCount(event.getId());
        
        // Send confirmation email
        emailService.sendRegistrationConfirmation(participant, event, registration.getConfirmationCode());
        
        return savedRegistration;
    }
    
    public List<EventRegistration> getRegistrationsByParticipant(Participant participant) {
        return registrationRepository.findByParticipant(participant);
    }
    
    public List<EventRegistration> getRegistrationsByEvent(Event event) {
        return registrationRepository.findByEvent(event);
    }
    
    public int getRegistrationCountForEvent(Event event) {
        return registrationRepository.countByEvent(event);
    }
    
    private String generateConfirmationCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
