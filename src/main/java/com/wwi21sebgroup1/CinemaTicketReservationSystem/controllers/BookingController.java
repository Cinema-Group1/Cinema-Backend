package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Booking;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.BookingRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    BookingRepository bookingRepository;
    @GetMapping("/all")
    public @ResponseBody Iterable<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }
}