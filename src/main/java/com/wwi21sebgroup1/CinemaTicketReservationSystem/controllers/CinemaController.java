package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Cinema;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaRepository cinemaRepository;

    @PutMapping("/add")
    public void addCinema(@RequestBody Cinema cinema){
        cinemaRepository.save(cinema);
    }
    @GetMapping("/all")
    public @ResponseBody Iterable<Cinema> getCinemas(){
        return cinemaRepository.findAll();
    }
}