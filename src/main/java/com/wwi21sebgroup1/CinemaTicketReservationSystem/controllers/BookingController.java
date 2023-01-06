package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Booking;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Showing;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.User;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/booking")
public class
BookingController {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    private ShowingRepository showingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SeatRepository seatRepository;

    @PostMapping("/add")
    public void addBooking(@RequestBody BookingRequest bookingRequest){
        bookingRepository.save(transformRequestToObject(bookingRequest));
    }

    @PostMapping("/update:{oldBookingId}")
    public void updateBooking(@PathVariable Integer oldBookingId, @RequestBody BookingRequest bookingRequest) {
        try {
            Booking updatedBooking = transformRequestToObject(bookingRequest);
            updatedBooking.setId(oldBookingId);
            bookingRepository.save(updatedBooking);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    @PostMapping("/delete:{oldBookingId}")
    public void deleteBooking(@PathVariable Integer oldBookingId){
        try{
            bookingRepository.deleteById(oldBookingId);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Booking transformRequestToObject(BookingRequest bookingRequest){
        Showing showing = showingRepository.findById(bookingRequest.getShowingId()).get();
        User user = userRepository.findById(bookingRequest.getUserId()).get();
        List<Seat> seats = new ArrayList<>();
        for(int id: bookingRequest.getSeatIds()){
            seats.add(seatRepository.findById(id).get());
        }
        return new Booking(user,showing,seats, bookingRequest.getPrice());
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }
}