package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatingPlanService {
    @Autowired
    SeatingPlanRepository seatingPlanRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    SeatNumberRepository seatNumberRepository;
    @Autowired
    SeatingPlanTemplateRepository seatingPlanTemplateRepository;

    public SeatingPlan addSeatingPlan(Showing showing){
        SeatingPlanTemplate seatingPlanTemplate = seatingPlanTemplateRepository.findByCinemaHallId(showing.getCinemaHall().getId()).get();
        SeatingPlan seatingPlan = new SeatingPlan(showing);
        seatingPlanRepository.save(seatingPlan);
        Iterable<SeatNumber> seatNumbers = seatNumberRepository.findAllBySeatingPlanTemplateId(seatingPlanTemplate.getId());
        for (SeatNumber seatNumber : seatNumbers) {
            seatRepository.save(new Seat(showing.getPricePerSeat(), false, seatingPlan, seatNumber));
        }
        return seatingPlan;
    }
    public Iterable<SeatingPlan> getAllSeatingPlans(){
        return seatingPlanRepository.findAll();
    }

    public SeatingPlan getSeatingPlanByShowing(Integer showingId){
        return seatingPlanRepository.findByShowingId(showingId);
    }
}
