package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.SeatBookedException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.ShowingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class ShowingService {
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
    private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private SeatNumberRepository seatNumberRepository;

    public void addShowing(ShowingRequest showingRequest){
        showingRepository.save(transformRequestToObject(showingRequest));
    }

    public void updateShowing(Integer id, ShowingRequest showingRequest){
        try {
            Showing updatedShowing = transformRequestToObject(showingRequest);
            updatedShowing.setId(id);
            showingRepository.save(updatedShowing);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void deleteShowing(Integer id){
        try {
            movieRepository.deleteById(id);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Iterable<Showing> getAllShowings(){
        return showingRepository.findAll();
    }

    public void book(BookingRequest bookingRequest){
        Booking booking = bookingService.transformRequestToObject(bookingRequest);
        Showing showing = booking.getShowing();
        Iterable<Seat> allSeatsFromShowing = seatRepository.findAllBySeatingPlanId(showing.getSeatingPlan().getId());
        for(Seat seat: allSeatsFromShowing){
            if(seat.isOccupied()){
                //
            } else{
                seat.setOccupied(true);
                seatRepository.save(seat);
                booking.getSeats().add(seat);
            }
        }
        bookingRepository.save(booking);
    }

    public Showing transformRequestToObject(ShowingRequest showingRequest) {
        Movie movie = movieRepository.findById(showingRequest.getMovieId()).get();
        CinemaHall cinemaHall = cinemaHallRepository.findById(showingRequest.getCinemaHallId()).get();
        SeatingPlan seatingPlan = new SeatingPlan();
        seatingPlanRepository.save(seatingPlan);
        for (SeatNumber seatNumber : cinemaHall.getSeatingPlanTemplate().getSeatNumbers()) {
            Seat curr = new Seat(10, false, seatingPlan, seatNumber);
            seatRepository.save(curr);
        }
        return new Showing( showingRequest.getTitle(),
                LocalDateTime.parse(showingRequest.getStartsAt()),
                LocalDateTime.parse(showingRequest.getEndsAt()),
                movie,
                cinemaHall,
                seatingPlan);
    }
}
