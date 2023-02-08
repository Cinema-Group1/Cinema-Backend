package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.SeatBookedException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.ShowingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SeatingPlanTemplateRepository seatingPlanTemplateRepository;
    @Autowired
    private SeatingPlanService seatingPlanService;

    public Showing addShowing(ShowingRequest showingRequest) throws InvalidRequestException {
        Showing showing = processRequest(showingRequest);
        showingRepository.save(showing);
        seatingPlanService.addSeatingPlan(showing);
        return showing;
    }
    public Iterable<Showing> getAllShowings(){
        return showingRepository.findAll();
    }

    public Iterable<Showing> getAllShowingsInFuture(){
        ArrayList<Showing> showingsInFuture = new ArrayList<>();
        Iterator<Showing> iterator = showingRepository.findAll().iterator();
        Showing curr;
        while (iterator.hasNext()){
            curr = iterator.next();
            if(curr.getStartsAt().isAfter(LocalDateTime.now())){
                showingsInFuture.add(curr);
            }
        }
        return showingsInFuture;
    }

    public Iterable<Showing> getShowingsByMovieId(Integer movieId){
        return showingRepository.findByMovieId(movieId);
    }

    public Iterable<Showing> getShowingsByDate(String dateString){
        return showingRepository.findByStartsAt(LocalDateTime.parse(dateString));
    }

    public Showing updateShowing(Integer id, ShowingRequest showingRequest) throws InvalidRequestException, NoSuchElementException{
        showingRepository.findById(id);
        Showing updatedShowing = processRequest(showingRequest);
        updatedShowing.setId(id);
        showingRepository.save(updatedShowing);
        return updatedShowing;
    }

    public void deleteShowing(Integer id){
        try {
            SeatingPlan seatingPlanToBeDeleted = seatingPlanRepository.findByShowingId(id);
            bookingRepository.deleteByShowingId(id);
            seatRepository.deleteAllBySeatingPlanId(seatingPlanToBeDeleted.getId());
            showingRepository.deleteById(id);
            seatingPlanRepository.deleteByShowingId(id);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Booking book(BookingRequest bookingRequest) throws SeatBookedException{
        Booking booking = bookingService.processRequest(bookingRequest);
        for(Seat seat : booking.getSeats()){
            if(seat.isOccupied()){
                throw new SeatBookedException();
            } else{
                seat.setOccupied(true);
                seatRepository.save(seat);
            }
        }
        bookingRepository.save(booking);
        for(Seat seat : booking.getSeats()){
            ticketRepository.save(new Ticket(booking.getShowing(), seat.getSeatNumber(), booking));
        }
        return booking;
    }

    public Showing processRequest(ShowingRequest showingRequest) throws InvalidRequestException, NoSuchElementException{
        if(showingRequest.getTitle() == null || showingRequest.getCinemaHallId() == null ||
           showingRequest.getStartsAt() == null || showingRequest.getEndsAt() == null ||
           showingRequest.getCinemaHallId() == null || showingRequest.getMovieId() == null){
            throw new InvalidRequestException("ShowingRequest");
        }
        Movie movie = movieRepository.findById(showingRequest.getMovieId()).get();
        CinemaHall cinemaHall = cinemaHallRepository.findById(showingRequest.getCinemaHallId()).get();
        Showing showing = new Showing( showingRequest.getTitle(),
                LocalDateTime.parse(showingRequest.getStartsAt()),
                LocalDateTime.parse(showingRequest.getEndsAt()),
                movie,
                cinemaHall,
                showingRequest.getPricePerSeat());
        return showing;
    }
}
