package com.culturals.registration.controller;

import com.culturals.registration.domain.Participant;
import com.culturals.registration.service.ParticipantService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/participant")
public class ParticipantController {
    
    @Autowired
    private ParticipantService participantService;
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("participant", new Participant());
        return "participant-register";
    }
    
    @PostMapping("/register")
    public String registerParticipant(@ModelAttribute Participant participant, Model model) {
        try {
            participantService.registerParticipant(participant);
            model.addAttribute("message", "Registration successful! Please login.");
            return "redirect:/participant/login?success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "participant-register";
        }
    }
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "participant-login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String email, 
                       @RequestParam String password, 
                       HttpSession session, 
                       Model model) {
        if (participantService.authenticate(email, password)) {
            Participant participant = participantService.findByEmail(email).get();
            session.setAttribute("participantId", participant.getId());
            session.setAttribute("participant", participant);
            return "redirect:/participant/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "participant-login";
        }
    }
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Participant participant = (Participant) session.getAttribute("participant");
        if (participant == null) {
            return "redirect:/participant/login";
        }
        model.addAttribute("participant", participant);
        return "participant-dashboard";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
