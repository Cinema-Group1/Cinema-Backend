package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatingPlanTemplateRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.SeatingPlanTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/seating_plan_template")
public class SeatingPlanTemplateController {
    @Autowired
    private SeatingPlanTemplateService seatingPlanTemplateService;

    @PutMapping("/add")
    public @ResponseBody ResponseEntity<Object> addSeatingPlanTemplate(@RequestBody SeatingPlanTemplateRequest seatingPlanTemplateRequest){
        try{
            return new ResponseEntity<>(seatingPlanTemplateService.addSeatingPlanTemplate(seatingPlanTemplateRequest), HttpStatus.ACCEPTED);
        }catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<Object> getAllSeatingPlanTemplates(){
        return new ResponseEntity<>(seatingPlanTemplateService.getAllSeatingPlanTemplates(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/cinemaHall:{cinemaHallId}")
    public @ResponseBody ResponseEntity<Object> getSeatingPlanTemplateByCinemaHallId(@PathVariable Integer cinemaHallId){
        return new ResponseEntity<>(seatingPlanTemplateService.getByCinemaHallId(cinemaHallId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update:{id}")
    public @ResponseBody ResponseEntity<Object> updateSeatingPlanTemplate(@PathVariable Integer id, @RequestBody SeatingPlanTemplateRequest seatingPlantemplateRequest){
        try{
            return new ResponseEntity<>(seatingPlanTemplateService.updateSeatingPlanTemplate(id, seatingPlantemplateRequest), HttpStatus.ACCEPTED);
        }catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete:{id}")
    public @ResponseBody ResponseEntity<Object> deleteSeatingPlanTemplate(@PathVariable Integer id) {
        try{
            seatingPlanTemplateService.deleteSeatingPlanTemplate(id);
            return new ResponseEntity<>("Successfully deleted SeatingPlanTemplate with Id: " + id, HttpStatus.ACCEPTED);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }
}
