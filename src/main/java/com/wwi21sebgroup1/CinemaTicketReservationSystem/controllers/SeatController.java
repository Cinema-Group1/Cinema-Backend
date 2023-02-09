package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    SeatService seatService;

    @GetMapping("/showing:{id}")
    public @ResponseBody ResponseEntity<Object> getByShowingId(@PathVariable int id){
        return new ResponseEntity<>(seatService.getByShowingId(id), HttpStatus.ACCEPTED);
    }
}