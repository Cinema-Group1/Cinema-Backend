package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Booking;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class
BookingController {
    @Autowired
    BookingService bookingService;

    @PostMapping("/add")
    public void addBooking(@RequestBody BookingRequest bookingRequest){
        bookingService.addBooking(bookingRequest);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @PostMapping("/update:{id}")
    public void updateBooking(@PathVariable Integer id, @RequestBody BookingRequest bookingRequest) {
        bookingService.updateBooking(id, bookingRequest);
    }

    @PostMapping("/delete:{id}")
    public void deleteBooking(@PathVariable Integer id){
        bookingService.deleteBooking(id);
    }
}