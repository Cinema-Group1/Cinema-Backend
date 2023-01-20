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
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SeatingPlanTemplateRepository seatingPlanTemplateRepository;

    public void addShowing(ShowingRequest showingRequest){
        showingRepository.save(processRequest(showingRequest));
    }

    public void updateShowing(Integer id, ShowingRequest showingRequest){
        try {
            Showing updatedShowing = processRequest(showingRequest);
            updatedShowing.setId(id);
            showingRepository.save(updatedShowing);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
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

    public Iterable<Showing> getAllShowings(){
        return showingRepository.findAll();
    }

    public Iterable<Showing> getShowingsByMovieId(Integer movieId){
        return showingRepository.findByMovieId(movieId);
    }

    public Iterable<Showing> getShowingsByDate(String dateString){
        return showingRepository.findByStartsAt(LocalDateTime.parse(dateString));
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
            ticketRepository.save(new Ticket(booking.getShowing(), seat));
        }
        return booking;
    }

    public Showing processRequest(ShowingRequest showingRequest) {
        Movie movie = movieRepository.findById(showingRequest.getMovieId()).get();
        CinemaHall cinemaHall = cinemaHallRepository.findById(showingRequest.getCinemaHallId()).get();
        SeatingPlanTemplate seatingPlanTemplate = seatingPlanTemplateRepository.findByCinemaHallId(cinemaHall.getId()).get();
        SeatingPlan seatingPlan = new SeatingPlan();
        seatingPlanRepository.save(seatingPlan);
        Iterable<SeatNumber> seatNumbers = seatNumberRepository.findAllBySeatingPlanTemplateId(seatingPlanTemplate.getId());
        for (SeatNumber seatNumber : seatNumbers) {
            seatRepository.save(new Seat(showingRequest.getPricePerSeat(), false, seatingPlan, seatNumber));
        }
        return new Showing( showingRequest.getTitle(),
                            LocalDateTime.parse(showingRequest.getStartsAt()),
                            LocalDateTime.parse(showingRequest.getEndsAt()),
                            movie,
                            cinemaHall);
    }
}
