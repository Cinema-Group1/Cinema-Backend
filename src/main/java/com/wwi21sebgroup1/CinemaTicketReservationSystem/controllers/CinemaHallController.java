package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.CinemaHall;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaHallRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanTemplateRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.CinemaHallRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cinema_hall")
public class CinemaHallController {
    @Autowired
    private CinemaHallRepository cinemaHallRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SeatingPlanTemplateRepository seatingPlanTemplateRepository;

    @PutMapping("/add")
    public void addCinemaHall(@RequestBody CinemaHallRequest cinemaHallRequest){
        cinemaHallRepository.save(transformRequestToObject(cinemaHallRequest));
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<CinemaHall> getCinemaHalls(){
        return cinemaHallRepository.findAll();
    }

    @PutMapping("/update:{id}")
    public void updateCinemaHall(@PathVariable Integer id, @RequestBody CinemaHallRequest cinemaHallRequest){
        try{
            CinemaHall oldCinemaHall = cinemaHallRepository.findById(id).get();
            CinemaHall updatedCinemaHall = transformRequestToObject(cinemaHallRequest);
            updatedCinemaHall.setId(oldCinemaHall.getId());
            cinemaHallRepository.save(updatedCinemaHall);
        } catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public CinemaHall transformRequestToObject(CinemaHallRequest cinemaHallRequest){
        Cinema cinema = cinemaRepository.findById(cinemaHallRequest.getCinemaId()).get();
        SeatingPlanTemplate seatingPlanTemplate = seatingPlanTemplateRepository.findById(cinemaHallRequest.getSeatingPlanTemplateId()).get();
        return new CinemaHall(  cinema,
                                cinemaHallRequest.getName(),
                                seatingPlanTemplate);
    }
}
