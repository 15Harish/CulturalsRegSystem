package com.culturals.registration.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Event name is required")
    private String eventName;
    
    @Column(length = 1000)
    private String description;
    
    @NotNull(message = "Duration is required")
    private Integer duration; // in minutes
    
    @NotNull(message = "Event time is required")
    private LocalDateTime eventTime;
    
    @NotBlank(message = "Venue is required")
    private String venue;
    
    @NotNull(message = "Maximum participants required")
    private Integer maxParticipants;
    
    @Column(length = 1000)
    private String rules;
    
    private String dressCode;
    
    private String languagePreference;
    
    private String coordinatorName;
    
    private String coordinatorContact;
    
    @Column(length = 500)
    private String imageUrl;
    
    private Boolean isVisible = true;
    
    private Integer registeredCount = 0;
    
    @NotNull(message = "Registration deadline is required")
    private LocalDateTime registrationDeadline;
    
    // Constructors
    public Event() {}
    
    public Event(String eventName, String description, Integer duration, 
                LocalDateTime eventTime, String venue, Integer maxParticipants,
                String rules, String dressCode, String languagePreference,
                String coordinatorName, String coordinatorContact, 
                String imageUrl, LocalDateTime registrationDeadline) {
        this.eventName = eventName;
        this.description = description;
        this.duration = duration;
        this.eventTime = eventTime;
        this.venue = venue;
        this.maxParticipants = maxParticipants;
        this.rules = rules;
        this.dressCode = dressCode;
        this.languagePreference = languagePreference;
        this.coordinatorName = coordinatorName;
        this.coordinatorContact = coordinatorContact;
        this.imageUrl = imageUrl;
        this.registrationDeadline = registrationDeadline;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public LocalDateTime getEventTime() {
        return eventTime;
    }
    
    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }
    
    public String getVenue() {
        return venue;
    }
    
    public void setVenue(String venue) {
        this.venue = venue;
    }
    
    public Integer getMaxParticipants() {
        return maxParticipants;
    }
    
    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
    
    public String getRules() {
        return rules;
    }
    
    public void setRules(String rules) {
        this.rules = rules;
    }
    
    public String getDressCode() {
        return dressCode;
    }
    
    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }
    
    public String getLanguagePreference() {
        return languagePreference;
    }
    
    public void setLanguagePreference(String languagePreference) {
        this.languagePreference = languagePreference;
    }
    
    public String getCoordinatorName() {
        return coordinatorName;
    }
    
    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }
    
    public String getCoordinatorContact() {
        return coordinatorContact;
    }
    
    public void setCoordinatorContact(String coordinatorContact) {
        this.coordinatorContact = coordinatorContact;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public Boolean getIsVisible() {
        return isVisible;
    }
    
    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public Integer getRegisteredCount() {
        return registeredCount;
    }
    
    public void setRegisteredCount(Integer registeredCount) {
        this.registeredCount = registeredCount;
    }
    
    public LocalDateTime getRegistrationDeadline() {
        return registrationDeadline;
    }
    
    public void setRegistrationDeadline(LocalDateTime registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }
    
    public boolean isFull() {
        return registeredCount >= maxParticipants;
    }
}
