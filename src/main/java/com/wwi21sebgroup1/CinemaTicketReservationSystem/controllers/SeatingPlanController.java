package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlan;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.SeatingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seating_plan")
public class SeatingPlanController {
    @Autowired
    private SeatingPlanService seatingPlanService;

    @GetMapping("/all")
    public @ResponseBody Iterable<SeatingPlan> getAllSeatingPlans(){
        return seatingPlanService.getAllSeatingPlans();
    }

    @GetMapping("showing:{showingId")
    public @ResponseBody SeatingPlan getSeatsBySeatingPlan(@PathVariable Integer showingId){
        return seatingPlanService.getSeatingPlanByShowing(showingId);
    }
}
