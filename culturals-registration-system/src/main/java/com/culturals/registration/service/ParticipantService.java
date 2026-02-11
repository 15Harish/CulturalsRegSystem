package com.culturals.registration.service;

import com.culturals.registration.domain.Participant;
import com.culturals.registration.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ParticipantService {
    
    @Autowired
    private ParticipantRepository participantRepository;
    
    @Transactional
    public Participant registerParticipant(Participant participant) {
        // Check if email already exists
        if (participantRepository.existsByEmail(participant.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        
        // Check if roll number already exists
        if (participantRepository.existsByRollNumber(participant.getRollNumber())) {
            throw new RuntimeException("Roll number already registered");
        }
        
        return participantRepository.save(participant);
    }
    
    public boolean authenticate(String email, String password) {
        Optional<Participant> participant = participantRepository.findByEmailAndPassword(email, password);
        return participant.isPresent();
    }
    
    public Optional<Participant> findByEmail(String email) {
        return participantRepository.findByEmail(email);
    }
    
    public Optional<Participant> findById(Long id) {
        return participantRepository.findById(id);
    }
}
