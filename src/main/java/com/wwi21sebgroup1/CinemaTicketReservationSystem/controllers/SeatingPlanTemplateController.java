package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlanTemplate;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seating_plan_template")
public class SeatingPlanTemplateController {
    @Autowired
    SeatingPlanTemplateRepository seatingPlanTemplateRepository;
    @GetMapping("/all")
    public @ResponseBody Iterable<SeatingPlanTemplate> getSeatingPlanTemplates(){
        return seatingPlanTemplateRepository.findAll();
    }
}
