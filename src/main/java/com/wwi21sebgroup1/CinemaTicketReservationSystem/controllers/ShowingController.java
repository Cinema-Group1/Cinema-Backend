package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.SeatBookedException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.ShowingRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
@RequestMapping(path="/showing")
public class ShowingController {
    @Autowired
    private ShowingService showingService;

    @PutMapping("/add")
    //Adding a Showing specified via the RequestBody.
    //Request Body takes in a JSON File and translates it to a Java Object.
    public void addShowing(@RequestBody ShowingRequest showingRequest) {
        showingService.addShowing(showingRequest);
    }

    @PostMapping("/update:{id}")
    public void updateShowing(@PathVariable Integer id, @RequestBody ShowingRequest showingRequest) {
        showingService.updateShowing(id, showingRequest);
    }

    @Transactional
    @DeleteMapping("/delete:{id}")
    public void deleteShowing(@PathVariable Integer id) {
        showingService.deleteShowing(id);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Showing> getShowings() {
        return showingService.getAllShowings();
    }

    @GetMapping("/movie:{movieId}")
    public @ResponseBody Iterable<Showing> getShowingsByMovieId(@PathVariable Integer movieId){
        return showingService.getShowingsByMovieId(movieId);
    }

    @GetMapping("/movie:{date}")
    public @ResponseBody Iterable<Showing> getShowingsByDate(@PathVariable String date){
        return showingService.getShowingsByDate(date);
    }

    @PutMapping("/book")
    public void bookShowing(@RequestBody BookingRequest bookingRequest){
        try{
            showingService.book(bookingRequest);
        }catch(SeatBookedException seatBookedException){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS, "Seat was already booked!", seatBookedException);
        }
    }
}