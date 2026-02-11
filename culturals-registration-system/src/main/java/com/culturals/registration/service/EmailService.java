package com.culturals.registration.service;

import com.culturals.registration.domain.Event;
import com.culturals.registration.domain.Participant;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendRegistrationConfirmation(Participant participant, Event event, String confirmationCode) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setTo(participant.getEmail());
            helper.setSubject("Event Registration Confirmation - " + event.getEventName());
            
            String emailContent = buildRegistrationEmail(participant, event, confirmationCode);
            helper.setText(emailContent, true);
            
            mailSender.send(message);
            System.out.println("Confirmation email sent to: " + participant.getEmail());
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void sendOTP(String email, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setTo(email);
            helper.setSubject("OTP for Culturals Registration");
            
            String emailContent = "<html><body>" +
                "<h2>Your OTP Code</h2>" +
                "<p>Your OTP for registration is: <strong>" + otp + "</strong></p>" +
                "<p>This OTP is valid for 10 minutes.</p>" +
                "<p>If you did not request this OTP, please ignore this email.</p>" +
                "</body></html>";
            
            helper.setText(emailContent, true);
            
            mailSender.send(message);
            System.out.println("OTP email sent to: " + email);
        } catch (Exception e) {
            System.err.println("Error sending OTP email: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private String buildRegistrationEmail(Participant participant, Event event, String confirmationCode) {
        return "<html><body>" +
            "<h2>Registration Confirmation</h2>" +
            "<p>Dear " + participant.getName() + ",</p>" +
            "<p>You have successfully registered for <strong>" + event.getEventName() + "</strong></p>" +
            "<h3>Event Details:</h3>" +
            "<ul>" +
            "<li><strong>Event:</strong> " + event.getEventName() + "</li>" +
            "<li><strong>Date & Time:</strong> " + event.getEventTime() + "</li>" +
            "<li><strong>Venue:</strong> " + event.getVenue() + "</li>" +
            "<li><strong>Duration:</strong> " + event.getDuration() + " minutes</li>" +
            "</ul>" +
            "<h3>Your Registration Details:</h3>" +
            "<ul>" +
            "<li><strong>Confirmation Code:</strong> " + confirmationCode + "</li>" +
            "<li><strong>Department:</strong> " + participant.getDepartment() + "</li>" +
            "<li><strong>Roll Number:</strong> " + participant.getRollNumber() + "</li>" +
            "</ul>" +
            "<p>Please keep this confirmation code for your records.</p>" +
            "<p>For any queries, contact the event coordinator:</p>" +
            "<p>" + event.getCoordinatorName() + " - " + event.getCoordinatorContact() + "</p>" +
            "<br>" +
            "<p>Best regards,<br>College Culturals Team</p>" +
            "</body></html>";
    }
}
