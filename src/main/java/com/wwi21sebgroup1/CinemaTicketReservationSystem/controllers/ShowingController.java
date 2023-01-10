package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.ShowingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path="/showing")
public class ShowingController {
    @Autowired
    private ShowingRepository showingRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CinemaHallRepository cinemaHallRepository;
    @Autowired
    private SeatingPlanRepository seatingPlanRepository;
    @Autowired
    private SeatRepository seatRepository;

    @PutMapping("/add")
    //Adding a Showing specified via the RequestBody.
    //Request Body takes in a JSON File and translates it to a Java Object.
    public void addShowing(@RequestBody ShowingRequest showingRequest) {
        showingRepository.save(transformRequestToObject(showingRequest));
    }

    @PostMapping("/update:{oldShowingId}")
    public void updateShowing(@PathVariable Integer oldShowingId, @RequestBody ShowingRequest showingRequest) {
        try {
            Showing updatedShowing = transformRequestToObject(showingRequest);
            updatedShowing.setId(oldShowingId);
            showingRepository.save(updatedShowing);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    @PostMapping("/delete:{oldShowingId}")
    public void deleteShowing(@PathVariable Integer oldShowingId) {
        try {
            movieRepository.deleteById(oldShowingId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Showing transformRequestToObject(ShowingRequest showingRequest) {
        Movie movie = movieRepository.findById(showingRequest.getMovieId()).get();
        CinemaHall cinemaHall = cinemaHallRepository.findById(showingRequest.getCinemaHallId()).get();
        SeatingPlan seatingPlan = new SeatingPlan();
        for (SeatNumber seatNumber : cinemaHall.getSeatingPlanTemplate().getSeatNumbers()) {
            Seat curr = new Seat(seatNumber, 10, false);
            seatRepository.save(curr);
            seatingPlan.addSeat(curr);
        }
        seatingPlanRepository.save(seatingPlan);
        return new Showing( showingRequest.getTitle(),
                            Date.valueOf(showingRequest.getStartDate()),
                            Date.valueOf(showingRequest.getEndDate()),
                            Date.valueOf(showingRequest.getStartTime()),
                            Date.valueOf(showingRequest.getEndTime()),
                            movie,
                            cinemaHall,
                            seatingPlan);
    }

    @GetMapping("/all")
    public @ResponseBody
    Iterable<Showing> getShowings() {
        return showingRepository.findAll();
    }

    @PutMapping("/book")
    public void bookShowing(@RequestBody BookingRequest bookingRequest) {
        Showing showing = showingRepository.findById(bookingRequest.getShowingId()).get();
        for (int seatId: bookingRequest.getSeatIds()){
            Seat seat = seatRepository.findById(seatId).get();
            seat.setOccupied(true);
            seatRepository.save(seat);
        }
    }
}