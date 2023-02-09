package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/booking")
public class
BookingController {
    @Autowired
    BookingService bookingService;

    @PutMapping("/add")
    public @ResponseBody ResponseEntity<Object> addBooking(@RequestBody BookingRequest bookingRequest){
        try{
            return new ResponseEntity<>(bookingService.addBooking(bookingRequest), HttpStatus.ACCEPTED);
        }catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<Object> getAllBookings(){
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/user:{userId}")
    public @ResponseBody ResponseEntity<Object> getBookingsByUserId(@PathVariable Integer userId){
        try{
            return new ResponseEntity<>(bookingService.getBookingsByUserId(userId), HttpStatus.ACCEPTED);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update:{id}")
    public @ResponseBody ResponseEntity<Object> updateBooking(@PathVariable Integer id, @RequestBody BookingRequest bookingRequest) {
        try{
            return new ResponseEntity<>(bookingService.updateBooking(id, bookingRequest), HttpStatus.ACCEPTED);
        }catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/delete:{id}")
    public @ResponseBody ResponseEntity<Object> deleteBooking(@PathVariable Integer id){
        try{
            bookingService.deleteBooking(id);
            return new ResponseEntity<>("Successfully deleted booking with Id: " + id, HttpStatus.ACCEPTED);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }
}