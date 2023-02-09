package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlan;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    SeatingPlanRepository seatingPlanRepository;

    public Iterable<Seat> getByShowingId(int showingId){
        SeatingPlan seatingPlan = seatingPlanRepository.findByShowingId(showingId);
        return seatRepository.findAllBySeatingPlanId(seatingPlan.getId());
    }
}
