package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlan;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlanTemplate;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanTemplateRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.MovieRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatingPlanTemplateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.chrono.IsoEra;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/seating_plan_template")
public class SeatingPlanTemplateController {
    @Autowired
    SeatingPlanTemplateRepository seatingPlanTemplateRepository;
    @Autowired
    SeatNumberRepository seatNumberRepository;
    @PutMapping("/add")
    public void addSeatingPlanTemplate(@RequestBody SeatingPlanTemplateRequest seatingPlanTemplateRequest){
        Set<SeatNumber> seatNumbers = new HashSet<SeatNumber>();
        for(int i = 1; i <= seatingPlanTemplateRequest.getRows(); i++){
            for(int j = 1; j <= seatingPlanTemplateRequest.getSeatsPerRow(); j++){
                SeatNumber currSeat = new SeatNumber((char)(i + 64), (byte)j);
                seatNumberRepository.save(currSeat);
                seatNumbers.add(currSeat);
            }
        }
        seatingPlanTemplateRepository.save(new SeatingPlanTemplate(seatNumbers));
    }
    @GetMapping("/all")
    public @ResponseBody Iterable<SeatingPlanTemplate> getSeatingPlanTemplates(){
        return seatingPlanTemplateRepository.findAll();
    }
}
