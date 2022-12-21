package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlan;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seating_plan")
public class SeatingPlanController {
    @Autowired
    SeatingPlanRepository seatingPlanRepository;
    @GetMapping("/all")
    public @ResponseBody Iterable<SeatingPlan> getSeatingPlans(){
        return seatingPlanRepository.findAll();
    }
}
