package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlan;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatingPlanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/seating_plan")
public class SeatingPlanController {
    @Autowired
    SeatingPlanRepository seatingPlanRepository;
    @Autowired
    SeatRepository seatRepository;

    @PostMapping("/add")
    public void addSeatingPlan(SeatingPlanRequest seatingPlanRequest){
        seatingPlanRepository.save(transformRequestToObject(seatingPlanRequest));
    }

    @PostMapping("/update:{oldSeatingPlanId}")
    public void updateSeatingPlan(@PathVariable Integer oldSeatingPlanId, @RequestBody SeatingPlanRequest seatingPlanRequest){
        try{
            SeatingPlan updatedSeatingPlan = transformRequestToObject(seatingPlanRequest);
            updatedSeatingPlan.setId(oldSeatingPlanId);
            seatingPlanRepository.save(updatedSeatingPlan);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    @PostMapping("/delete:{oldSeatingPlanId}")
    public void deleteSeatingPlan(@PathVariable Integer oldSeatingPlanId) {
        try {
            seatingPlanRepository.deleteById(oldSeatingPlanId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public SeatingPlan transformRequestToObject(SeatingPlanRequest seatingPlanRequest){
        List<Seat> seats = new ArrayList<>();
        for(int seatId: seatingPlanRequest.getSeatIds()){
            seats.add(seatRepository.findById(seatId).get());
        }
        return new SeatingPlan(seats);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<SeatingPlan> getSeatingPlans(){
        return seatingPlanRepository.findAll();
    }
}
