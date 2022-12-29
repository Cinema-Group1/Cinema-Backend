package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.CinemaHall;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinema_hall")
public class CinemaHallController {
    @Autowired
    private CinemaHallRepository cinemaHallRepository;

    @PutMapping("/add")
    public void addCinemaHall(@RequestBody CinemaHall cinemaHall){
        cinemaHallRepository.save(cinemaHall);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<CinemaHall> getCinemaHalls(){
        return cinemaHallRepository.findAll();
    }
}
