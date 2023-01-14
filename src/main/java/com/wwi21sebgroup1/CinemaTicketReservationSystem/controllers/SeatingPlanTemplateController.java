package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlanTemplate;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatingPlanTemplateRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.SeatingPlanTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/seating_plan_template")
public class SeatingPlanTemplateController {
    @Autowired
    private SeatingPlanTemplateService seatingPlanTemplateService;

    @PutMapping("/add")
    public void addSeatingPlanTemplate(@RequestBody SeatingPlanTemplateRequest seatingPlanTemplateRequest){
        seatingPlanTemplateService.addSeatingPlanTemplate(seatingPlanTemplateRequest);
    }

    @GetMapping("/showing:{showingId}")
    public @ResponseBody Iterable<SeatingPlanTemplate> getByShowingId(@PathVariable Integer showingId){
        return seatingPlanTemplateService.getAllSeatingPlanTemplates();
    }

    @PutMapping("/update:{id}")
    public void updateSeatingPlanTemplate(@PathVariable Integer id, @RequestBody SeatingPlanTemplateRequest seatingPlantemplateRequest){
        seatingPlanTemplateService.updateSeatingPlanTemplate(id, seatingPlantemplateRequest);
    }

    @DeleteMapping("/delete:{id}")
    public void deleteSeatingPlanTemplate(@PathVariable Integer id) {
        seatingPlanTemplateService.deleteSeatingPlanTemplate(id);
    }
}
