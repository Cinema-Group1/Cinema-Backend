package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlanTemplate;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SeatingPlanTemplate")
public class SeatingPlanTemplateController {
    @Autowired
    SeatingPlanTemplateRepository seatingPlanTemplateRepository;
    @GetMapping
    public @ResponseBody Iterable<SeatingPlanTemplate> getSeatingPlanTemplates(){
        return seatingPlanTemplateRepository.findAll();
    }
}
