package com.culturals.registration.service;

import com.culturals.registration.domain.Event;
import com.culturals.registration.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;
    
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    
    public List<Event> getVisibleEvents() {
        return eventRepository.findByIsVisible(true);
    }
    
    public List<Event> getAvailableEvents() {
        return eventRepository.findByIsVisibleAndRegistrationDeadlineAfter(true, LocalDateTime.now());
    }
    
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }
    
    @Transactional
    public Event createEvent(Event event) {
        event.setRegisteredCount(0);
        event.setIsVisible(true);
        return eventRepository.save(event);
    }
    
    @Transactional
    public Event updateEvent(Long id, Event updatedEvent) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        
        event.setEventName(updatedEvent.getEventName());
        event.setDescription(updatedEvent.getDescription());
        event.setDuration(updatedEvent.getDuration());
        event.setEventTime(updatedEvent.getEventTime());
        event.setVenue(updatedEvent.getVenue());
        event.setMaxParticipants(updatedEvent.getMaxParticipants());
        event.setRules(updatedEvent.getRules());
        event.setDressCode(updatedEvent.getDressCode());
        event.setLanguagePreference(updatedEvent.getLanguagePreference());
        event.setCoordinatorName(updatedEvent.getCoordinatorName());
        event.setCoordinatorContact(updatedEvent.getCoordinatorContact());
        event.setImageUrl(updatedEvent.getImageUrl());
        event.setRegistrationDeadline(updatedEvent.getRegistrationDeadline());
        
        return eventRepository.save(event);
    }
    
    @Transactional
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
    
    @Transactional
    public void hideEvent(Long id) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        event.setIsVisible(false);
        eventRepository.save(event);
    }
    
    @Transactional
    public void incrementRegisteredCount(Long eventId) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        event.setRegisteredCount(event.getRegisteredCount() + 1);
        
        // Auto-hide if full
        if (event.isFull()) {
            event.setIsVisible(false);
        }
        
        eventRepository.save(event);
    }
}
