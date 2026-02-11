package com.culturals.registration.repository;

import com.culturals.registration.domain.Event;
import com.culturals.registration.domain.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, Long> {
    
    List<Winner> findByEvent(Event event);
    
    List<Winner> findByEventOrderByPositionAsc(Event event);
    
    @Query("SELECT w.participant.department, SUM(w.points) FROM Winner w GROUP BY w.participant.department ORDER BY SUM(w.points) DESC")
    List<Object[]> getDepartmentPoints();
}
