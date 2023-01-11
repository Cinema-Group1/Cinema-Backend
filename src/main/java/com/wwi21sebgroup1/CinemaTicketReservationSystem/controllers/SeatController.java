package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Showing;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.ShowingRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private SeatNumberRepository seatNumberRepository;
    @Autowired
    private ShowingRepository showingRepository;

    @PostMapping("/add")
    public void addSeat(@RequestBody SeatRequest seatRequest){
        seatRepository.save(transformRequestToObject(seatRequest));
    }

    @GetMapping("/showing:{id}")
    public Iterable<Seat> getSeatsByShowing(@PathVariable Integer id){
        Showing showing = showingRepository.findById(id).get();
        return seatRepository.findAllBySeatingPlanId(showing.getSeatingPlan().getId());
    }

    @PostMapping("/update:{oldSeatId}")
    public void updateSeat(@PathVariable Integer oldSeatId, @RequestBody SeatRequest seatRequest){
        try {
            Seat updatedSeat = transformRequestToObject(seatRequest);
            updatedSeat.setId(oldSeatId);
            seatRepository.save(updatedSeat);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    @PostMapping("/delete:{oldSeatId}")
    public void deleteSeat(@PathVariable Integer oldSeatId) {
        try {
            seatRepository.deleteById(oldSeatId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Seat transformRequestToObject(SeatRequest seatRequest){
        SeatNumber seatNumber = seatNumberRepository.findById(seatRequest.getSeatNumberId()).get();
        Showing showing = showingRepository.findById(seatRequest.getShowingId()).get();
        return new Seat(seatRequest.getPrice(), seatRequest.isOccupied(), showing.getSeatingPlan(), seatNumber);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Seat> getSeats(){
        return seatRepository.findAll();
    }
}