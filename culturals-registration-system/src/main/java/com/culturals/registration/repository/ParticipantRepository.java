package com.culturals.registration.repository;

import com.culturals.registration.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    
    Optional<Participant> findByEmail(String email);
    
    Optional<Participant> findByRollNumber(String rollNumber);
    
    Optional<Participant> findByEmailAndPassword(String email, String password);
    
    boolean existsByEmail(String email);
    
    boolean existsByRollNumber(String rollNumber);
}
