package com.culturals.registration.controller;

import com.culturals.registration.domain.Event;
import com.culturals.registration.domain.EventRegistration;
import com.culturals.registration.domain.Participant;
import com.culturals.registration.domain.Winner;
import com.culturals.registration.service.EventRegistrationService;
import com.culturals.registration.service.EventService;
import com.culturals.registration.service.ParticipantService;
import com.culturals.registration.service.WinnerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private EventRegistrationService registrationService;
    
    @Autowired
    private ParticipantService participantService;
    
    @Autowired
    private WinnerService winnerService;
    
    // Participant views events
    @GetMapping("/available")
    public String viewAvailableEvents(HttpSession session, Model model) {
        Participant participant = (Participant) session.getAttribute("participant");
        if (participant == null) {
            return "redirect:/participant/login";
        }
        
        List<Event> events = eventService.getAvailableEvents();
        model.addAttribute("events", events);
        return "event-list";
    }
    
    // View event details
    @GetMapping("/{id}")
    public String viewEventDetails(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        model.addAttribute("event", event);
        return "event-details";
    }
    
    // Register for event
    @GetMapping("/{id}/register")
    public String showRegistrationForm(@PathVariable Long id, HttpSession session, Model model) {
        Participant participant = (Participant) session.getAttribute("participant");
        if (participant == null) {
            return "redirect:/participant/login";
        }
        
        Event event = eventService.getEventById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        
        model.addAttribute("event", event);
        model.addAttribute("registration", new EventRegistration());
        return "event-registration";
    }
    
    @PostMapping("/{id}/register")
    public String registerForEvent(@PathVariable Long id, 
                                  @RequestParam String specialRequirements,
                                  HttpSession session, 
                                  Model model) {
        Participant participant = (Participant) session.getAttribute("participant");
        if (participant == null) {
            return "redirect:/participant/login";
        }
        
        try {
            Event event = eventService.getEventById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
            
            registrationService.registerForEvent(participant, event, specialRequirements);
            return "redirect:/events/my-registrations?success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/events/" + id + "?error=" + e.getMessage();
        }
    }
    
    // View participant's registrations
    @GetMapping("/my-registrations")
    public String viewMyRegistrations(HttpSession session, Model model) {
        Participant participant = (Participant) session.getAttribute("participant");
        if (participant == null) {
            return "redirect:/participant/login";
        }
        
        List<EventRegistration> registrations = registrationService.getRegistrationsByParticipant(participant);
        model.addAttribute("registrations", registrations);
        return "my-registrations";
    }
    
    // Admin: Create event
    @GetMapping("/admin/create")
    public String showCreateEventForm(HttpSession session, Model model) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("event", new Event());
        return "admin-create-event";
    }
    
    @PostMapping("/admin/create")
    public String createEvent(@ModelAttribute Event event, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        eventService.createEvent(event);
        return "redirect:/admin/events";
    }
    
    // Admin: View all events
    @GetMapping("/admin/list")
    public String viewAllEvents(HttpSession session, Model model) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "admin-events";
    }
    
    // Admin: Edit event
    @GetMapping("/admin/{id}/edit")
    public String showEditEventForm(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        Event event = eventService.getEventById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        model.addAttribute("event", event);
        return "admin-edit-event";
    }
    
    @PostMapping("/admin/{id}/edit")
    public String updateEvent(@PathVariable Long id, @ModelAttribute Event event, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        eventService.updateEvent(id, event);
        return "redirect:/admin/events";
    }
    
    // Admin: Delete event
    @PostMapping("/admin/{id}/delete")
    public String deleteEvent(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        eventService.deleteEvent(id);
        return "redirect:/admin/events";
    }
    
    // Admin: Hide event
    @PostMapping("/admin/{id}/hide")
    public String hideEvent(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        eventService.hideEvent(id);
        return "redirect:/admin/events";
    }
    
    // Admin: View registrations for an event
    @GetMapping("/admin/{id}/registrations")
    public String viewEventRegistrations(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        
        Event event = eventService.getEventById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        List<EventRegistration> registrations = registrationService.getRegistrationsByEvent(event);
        
        model.addAttribute("event", event);
        model.addAttribute("registrations", registrations);
        return "admin-event-registrations";
    }
    
    // View winners
    @GetMapping("/{id}/winners")
    public String viewWinners(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        List<Winner> winners = winnerService.getWinnersByEvent(event);
        
        model.addAttribute("event", event);
        model.addAttribute("winners", winners);
        return "event-winners";
    }
}
