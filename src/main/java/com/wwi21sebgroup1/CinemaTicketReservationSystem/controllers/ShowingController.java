package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.SeatBookedException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.ShowingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;

    @PutMapping("/add")
    //Adding a Showing specified via the RequestBody.
    //Request Body takes in a JSON File and translates it to a Java Object.
    public void addShowing(@RequestBody ShowingRequest showingRequest){
        Movie movie = movieRepository.findById(showingRequest.getMovieId()).get();
        CinemaHall cinemaHall = cinemaHallRepository.findById(showingRequest.getCinemaHallId()).get();
        SeatingPlan seatingPlan = new SeatingPlan();
        for(SeatNumber seatNumber : cinemaHall.getSeatingPlanTemplate().getSeatNumbers()) {
            Seat curr = new Seat(seatNumber, 10, false);
            seatRepository.save(curr);
            seatingPlan.addSeat(curr);
        }
        seatingPlanRepository.save(seatingPlan);
        showingRepository.save(new Showing( showingRequest.getTitle(),
                                            showingRequest.getStartDate(),
                                            showingRequest.getEndDate(),
                                            showingRequest.getStartTime(),
                                            showingRequest.getEndTime(),
                                            movie,
                                            cinemaHall,
                                            seatingPlan));
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Showing> getShowings() {
        return showingRepository.findAll();
    }

    @PutMapping("/book")
    public void bookShowing(@RequestBody BookingRequest bookingRequest)throws SeatBookedException{
        Showing showing = showingRepository.findById(bookingRequest.getShowingId()).get();
        User user = userRepository.findById(bookingRequest.getUserId()).get();
        List<Seat> seats= new ArrayList<>();
        List<Integer> seatNumbers = bookingRequest.getSeats();
        for(int i = 0; i < seatNumbers.size(); i++){
            for(Seat toBook : showing.getSeatingPlan().getSeats()){
                if(seatNumbers.get(i).equals(toBook.getId())){
                    if(!toBook.isOccupied()){
                        toBook.setOccupied(true);
                        seatRepository.save(toBook);
                        seats.add(toBook);
                    } else{
                        throw new SeatBookedException();
                    }
                }
            }
        }
        int price = 0;
        for(Seat seat : seats){
            price += seat.getPrice();
        }
        bookingRepository.save(new Booking(user, showing, seats, price));
    }

    @DeleteMapping("/delete:{id}")
    public void deleteMapping(@PathVariable Integer id){
        showingRepository.delete(showingRepository.findById(id).get());
    }
}