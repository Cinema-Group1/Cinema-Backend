package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Cinema;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.CinemaRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @PutMapping("/add")
    public void addCinema(@RequestBody CinemaRequest cinemaRequest){cinemaService.addCinema(cinemaRequest);}

    @PostMapping("/update:{oldCinemaId}")
    public void updateCinema(@PathVariable Integer oldCinemaId, @RequestBody CinemaRequest cinemaRequest) {cinemaService.updateCinema(oldCinemaId, cinemaRequest);}

    @PostMapping("/delete:{oldCinemaId}")
    public void deleteCinema(@PathVariable Integer oldCinemaId) {cinemaService.deleteCinema(oldCinemaId);}

    @GetMapping("/all")
    public @ResponseBody Iterable<Cinema> getCinemas(){
        return cinemaService.getCinemas();
    }
}