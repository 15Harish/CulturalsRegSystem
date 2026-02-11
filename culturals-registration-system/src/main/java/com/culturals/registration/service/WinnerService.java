package com.culturals.registration.service;

import com.culturals.registration.domain.Event;
import com.culturals.registration.domain.Participant;
import com.culturals.registration.domain.Winner;
import com.culturals.registration.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WinnerService {
    
    @Autowired
    private WinnerRepository winnerRepository;
    
    @Transactional
    public Winner addWinner(Event event, Participant participant, Integer position, Integer points) {
        Winner winner = new Winner(event, participant, position, points);
        return winnerRepository.save(winner);
    }
    
    public List<Winner> getWinnersByEvent(Event event) {
        return winnerRepository.findByEventOrderByPositionAsc(event);
    }
    
    public Map<String, Integer> getDepartmentPoints() {
        List<Object[]> results = winnerRepository.getDepartmentPoints();
        Map<String, Integer> departmentPoints = new HashMap<>();
        
        for (Object[] result : results) {
            String department = (String) result[0];
            Long points = (Long) result[1];
            departmentPoints.put(department, points.intValue());
        }
        
        return departmentPoints;
    }
}
