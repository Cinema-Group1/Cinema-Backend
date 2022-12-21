package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Cinema;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaRepository cinemaRepository;
    @GetMapping
    public @ResponseBody Iterable<Cinema> getCinemas(){
        return cinemaRepository.findAll();
    }
}