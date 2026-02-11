-- Create Database
CREATE DATABASE IF NOT EXISTS culturals_db;
USE culturals_db;

-- The tables will be auto-created by Spring Boot JPA
-- This file contains initial data for testing

-- Insert Admin User (password: admin123)
INSERT INTO admins (username, email, password) 
VALUES ('admin', 'admin@college.edu', 'admin123');

-- Insert Sample Participants
INSERT INTO participants (name, department, phone_number, roll_number, email, gender, password)
VALUES 
('John Doe', 'CSE', '9876543210', '21CS001', 'john@college.edu', 'Male', 'password123'),
('Jane Smith', 'ECE', '9876543211', '21EC001', 'jane@college.edu', 'Female', 'password123'),
('Alex Johnson', 'MECH', '9876543212', '21ME001', 'alex@college.edu', 'Male', 'password123');

-- Insert Sample Events
INSERT INTO events (event_name, description, duration, event_time, venue, 
                    max_participants, rules, dress_code, language_preference,
                    coordinator_name, coordinator_contact, registration_deadline, 
                    is_visible, registered_count)
VALUES 
('Dance Competition', 
 'Showcase your dancing skills in this inter-department competition', 
 120, 
 '2024-03-15 14:00:00', 
 'Main Auditorium', 
 50, 
 '1. Solo or group (max 5 members)\n2. Time limit: 5-8 minutes\n3. Props allowed', 
 'Traditional or Western', 
 'Any', 
 'Prof. Sharma', 
 '9123456789', 
 '2024-03-10 23:59:59', 
 true, 
 0),

('Singing Competition', 
 'Show your vocal talent in this melodious event', 
 90, 
 '2024-03-16 10:00:00', 
 'Music Room', 
 30, 
 '1. Solo performance only\n2. Time limit: 3-5 minutes\n3. Instrumental track allowed', 
 'Formal', 
 'Hindi, English, Tamil', 
 'Prof. Kumar', 
 '9123456780', 
 '2024-03-11 23:59:59', 
 true, 
 0),

('Drama Competition', 
 'Perform a short play and win exciting prizes', 
 150, 
 '2024-03-17 15:00:00', 
 'Theater Hall', 
 40, 
 '1. Group performance (5-10 members)\n2. Time limit: 15-20 minutes\n3. Props and costumes allowed', 
 'As per theme', 
 'Any', 
 'Dr. Patel', 
 '9123456781', 
 '2024-03-12 23:59:59', 
 true, 
 0),

('Art Competition', 
 'Express your creativity through art', 
 180, 
 '2024-03-18 09:00:00', 
 'Art Studio', 
 60, 
 '1. Individual participation\n2. Theme will be given on spot\n3. Materials provided', 
 'Casual', 
 'N/A', 
 'Prof. Reddy', 
 '9123456782', 
 '2024-03-13 23:59:59', 
 true, 
 0),

('Quiz Competition', 
 'Test your knowledge across various subjects', 
 60, 
 '2024-03-19 11:00:00', 
 'Seminar Hall', 
 100, 
 '1. Team of 2 members\n2. Multiple rounds\n3. No mobile phones allowed', 
 'Formal', 
 'English', 
 'Dr. Singh', 
 '9123456783', 
 '2024-03-14 23:59:59', 
 true, 
 0);

-- Sample Event Registrations (uncomment after running once)
-- INSERT INTO event_registrations (participant_id, event_id, special_requirements, 
--                                  registration_time, confirmation_code, confirmed)
-- VALUES 
-- (1, 1, 'Need wireless microphone', NOW(), 'CONF001', true),
-- (2, 2, 'Prefer evening slot', NOW(), 'CONF002', true),
-- (3, 3, NULL, NOW(), 'CONF003', true);

-- Sample Winners (uncomment after events are completed)
-- INSERT INTO winners (event_id, participant_id, position, points)
-- VALUES 
-- (1, 1, 1, 100),
-- (1, 2, 2, 75),
-- (1, 3, 3, 50),
-- (2, 2, 1, 100),
-- (2, 1, 2, 75);

-- Query to check department points
-- SELECT p.department, SUM(w.points) as total_points
-- FROM winners w
-- JOIN participants p ON w.participant_id = p.id
-- GROUP BY p.department
-- ORDER BY total_points DESC;
