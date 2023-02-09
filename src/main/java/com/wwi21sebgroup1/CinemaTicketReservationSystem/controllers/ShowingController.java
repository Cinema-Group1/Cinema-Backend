package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.SeatBookedException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.ShowingRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path="/showing")
public class ShowingController {
    @Autowired
    private ShowingService showingService;

    @PutMapping("/add")
    public ResponseEntity<Object> addShowing(@RequestBody ShowingRequest showingRequest) {
        try{
            return new ResponseEntity<>(showingService.addShowing(showingRequest), HttpStatus.ACCEPTED);
        }catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Showing> getAllShowings() {
        return showingService.getAllShowings();
    }

    @GetMapping("/allInFuture")
    public @ResponseBody Iterable<Showing> getAllShowingsInFuture(){
        return showingService.getAllShowingsInFuture();
    }

    @GetMapping("/movieId:{movieId}")
    public @ResponseBody Iterable<Showing> getShowingsByMovieId(@PathVariable Integer movieId){
        return showingService.getShowingsByMovieId(movieId);
    }

    @PostMapping("/update:{id}")
    public ResponseEntity<Object> updateShowing(@PathVariable Integer id, @RequestBody ShowingRequest showingRequest) {
        try {
            return new ResponseEntity<>(showingService.updateShowing(id, showingRequest), HttpStatus.ACCEPTED);
        } catch (InvalidRequestException invalidRequestException) {
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete:{id}")
    public ResponseEntity<Object> deleteShowing(@PathVariable Integer id) {
        try{
            showingService.deleteShowing(id);
            return new ResponseEntity<>("Successfully deleted showing with Id: " + id, HttpStatus.ACCEPTED);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/book")
    public ResponseEntity<Object> bookShowing(@RequestBody BookingRequest bookingRequest){
        try{
            return new ResponseEntity<>(showingService.book(bookingRequest), HttpStatus.ACCEPTED);
        }catch(SeatBookedException seatBookedException){
            return new ResponseEntity<>(seatBookedException.toString(), HttpStatus.FORBIDDEN);
        }
    }
}