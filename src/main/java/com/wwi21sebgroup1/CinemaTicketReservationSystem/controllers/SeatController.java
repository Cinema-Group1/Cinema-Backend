package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping("/add")
    public void addSeat(@RequestBody SeatRequest seatRequest){seatService.addSeat(seatRequest);}

    @PostMapping("/update:{oldSeatId}")
    public void updateSeat(@PathVariable Integer oldSeatId, @RequestBody SeatRequest seatRequest){
        seatService.updateSeat(oldSeatId, seatRequest);
    }

    @PostMapping("/delete:{oldSeatId}")
    public void deleteSeat(@PathVariable Integer oldSeatId) {seatService.deleteSeat(oldSeatId);}

    @GetMapping("/all")
    public @ResponseBody Iterable<Seat> getSeats(){
        return seatService.getSeats();
    }

    @GetMapping("seating_plan_template:{seatingPlanTemplateId")
    public @ResponseBody Iterable<Seat> getSeatsBySeatingPlan(@PathVariable Integer seatingPlanTemplateId){
        return seatService.getSeatsBySeatingPlan(seatingPlanTemplateId);
    }
}