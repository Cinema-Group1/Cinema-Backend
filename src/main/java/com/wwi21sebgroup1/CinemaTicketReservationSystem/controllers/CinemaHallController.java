package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.CinemaHall;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaHallRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema_hall")
public class CinemaHallController {
    private CinemaHallRepository cinemaHallRepository;
    @GetMapping("/all")
    public @ResponseBody Iterable<CinemaHall> getCinemaHalls(){
        return cinemaHallRepository.findAll();
    }
}
