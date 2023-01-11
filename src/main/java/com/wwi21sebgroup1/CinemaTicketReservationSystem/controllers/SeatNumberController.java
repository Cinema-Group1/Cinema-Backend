package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatNumberRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.SeatNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/seat_number")
public class SeatNumberController {
    @Autowired
    SeatNumberService seatNumberService;

    @PostMapping("/add")
    public void addSeatNumber(@RequestBody SeatNumberRequest seatNumberRequest){
        seatNumberService.addSeatNumber(seatNumberRequest);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<SeatNumber> getAllSeatNumbers(){
        return seatNumberService.getAllSeatNumbers();
    }

    @PostMapping("/update:{id}")
    public void updateSeatNumber(@PathVariable Integer id, @RequestBody SeatNumberRequest seatNumberRequest){
        seatNumberService.updateSeatNumber(id, seatNumberRequest);
    }

    @PostMapping("/delete:{id}")
    public void deleteSeatNumber(@PathVariable Integer id) {
        deleteSeatNumber(id);
    }
}
