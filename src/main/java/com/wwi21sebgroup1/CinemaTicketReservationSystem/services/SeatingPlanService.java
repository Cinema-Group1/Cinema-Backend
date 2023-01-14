package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlan;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatingPlanService {
    @Autowired
    SeatingPlanRepository seatingPlanRepository;
    public Iterable<SeatingPlan> getAllSeatingPlans(){
        return seatingPlanRepository.findAll();
    }

    public SeatingPlan getSeatingPlanByShowing(Integer showingId){
        return seatingPlanRepository.findByShowingId(showingId);
    }
}
