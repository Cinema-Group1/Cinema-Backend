package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatNumberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/seat_number")
public class SeatNumberController {
    @Autowired
    SeatNumberRepository seatNumberRepository;

    @PostMapping("/add")
    public void addSeatNumber(SeatNumberRequest seatNumberRequest){
        seatNumberRepository.save(transformRequestToObject(seatNumberRequest));
    }

    @PostMapping("/update:{oldSeatNumberId}")
    public void updateSeatNumber(@PathVariable Integer oldSeatNumberId, @RequestBody SeatNumberRequest seatNumberRequest){
        try{
            SeatNumber updatedSeatNumber = transformRequestToObject(seatNumberRequest);
            updatedSeatNumber.setId(oldSeatNumberId);
            seatNumberRepository.save(updatedSeatNumber);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    @PostMapping("/delete:{oldSeatNumberId}")
    public void deleteSeatNumber(@PathVariable Integer oldSeatNumberId) {
        try {
            seatNumberRepository.deleteById(oldSeatNumberId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public SeatNumber transformRequestToObject(SeatNumberRequest seatNumberRequest){
        return new SeatNumber(seatNumberRequest.getLine(), seatNumberRequest.getNumber());
    }

    @GetMapping
    public @ResponseBody Iterable<SeatNumber> getAll(){
        return seatNumberRepository.findAll();
    }
}
