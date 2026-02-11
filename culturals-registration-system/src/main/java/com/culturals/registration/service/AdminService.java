package com.culturals.registration.service;

import com.culturals.registration.domain.Admin;
import com.culturals.registration.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    public boolean authenticate(String email, String password) {
        Optional<Admin> admin = adminRepository.findByEmailAndPassword(email, password);
        return admin.isPresent();
    }
    
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
