package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.CinemaHall;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.CinemaHallRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinema_hall")
public class CinemaHallController {
    @Autowired
    private CinemaHallService cinemaHallService;

    @PutMapping("/add")
    public void addCinemaHall(@RequestBody CinemaHallRequest cinemaHallRequest){cinemaHallService.addCinemaHall(cinemaHallRequest);}

    @PutMapping("/update:{oldCinemaHallId}")
    public void updateCinemaHall(@PathVariable Integer oldCinemaHallId, @RequestBody CinemaHallRequest cinemaHallRequest){cinemaHallService.updateCinemaHall(oldCinemaHallId, cinemaHallRequest);}

    @PutMapping("/delete:{oldCinemaHallId}")
    public void deleteCinemaHall(@PathVariable Integer oldCinemaHallId){cinemaHallService.deleteCinemaHall(oldCinemaHallId);}

    @GetMapping("/all")
    public @ResponseBody Iterable<CinemaHall> getCinemaHalls(){
        return cinemaHallService.getCinemaHalls();
    }
}
