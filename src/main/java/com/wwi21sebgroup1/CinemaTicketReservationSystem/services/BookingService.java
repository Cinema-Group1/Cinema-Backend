package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

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

    public void addBooking(BookingRequest bookingRequest){
        bookingRepository.save(processRequest(bookingRequest));
    }

    public Iterable<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public Iterable<Booking> getBookingsByUserId(Integer userId){
        return bookingRepository.getBookingByUserId(userId);
    }

    public void updateBooking(Integer id, BookingRequest bookingRequest){
        try {
            Booking updatedBooking = processRequest(bookingRequest);
            updatedBooking.setId(id);
            bookingRepository.save(updatedBooking);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void deleteBooking(Integer id){
        try{
            bookingRepository.deleteById(id);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Booking processRequest(BookingRequest bookingRequest){
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
