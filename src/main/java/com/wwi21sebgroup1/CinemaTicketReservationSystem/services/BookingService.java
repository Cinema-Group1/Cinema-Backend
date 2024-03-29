package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    private ShowingRepository showingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SeatingPlanRepository seatingPlanRepository;
    @Autowired
    private SeatRepository seatRepository;

    public Booking addBooking(BookingRequest bookingRequest) throws InvalidRequestException {
        Booking booking = processRequest(bookingRequest);
        bookingRepository.save(processRequest(bookingRequest));
        return booking;
    }

    public Iterable<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public Iterable<Booking> getBookingsByUserId(Integer userId){
        return bookingRepository.findByUserId(userId);
    }

    public Booking updateBooking(Integer id, BookingRequest bookingRequest) throws InvalidRequestException, NoSuchElementException {
        bookingRepository.findById(id);
        Booking updatedBooking = processRequest(bookingRequest);
        updatedBooking.setId(id);
        bookingRepository.save(updatedBooking);
        return updatedBooking;
    }

    public void deleteBooking(Integer id){
        bookingRepository.deleteById(id);
    }

    public Booking processRequest(BookingRequest bookingRequest) throws InvalidRequestException{
        Showing showing = showingRepository.findById(bookingRequest.getShowingId()).get();
        User user = userRepository.findById(bookingRequest.getUserId()).get();
        SeatingPlan seatingPlan = seatingPlanRepository.findByShowingId(showing.getId());
        Iterable<Seat> allSeats = seatRepository.findAllBySeatingPlanId(seatingPlan.getId());
        List<Seat> seatsToBook = new ArrayList<>();
        int totalPrice = 0;

        for(Seat seat : allSeats){
            for(String seatNumberString : bookingRequest.getSeatNumbers()){
                if(seat.getSeatNumber().getLine() == (seatNumberString.charAt(0)) &&
                    seat.getSeatNumber().getNumber() == Character.getNumericValue(seatNumberString.charAt(1))){
                    seatsToBook.add(seat);
                    totalPrice += seat.getPrice();
                }
            }
        }
        return new Booking(user, showing, seatsToBook, totalPrice);
    }
}
