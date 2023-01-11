package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.BookingRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.ShowingRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.UserRepository;
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
    private SeatNumberRepository seatNumberRepository;

    public void addBooking(BookingRequest bookingRequest){
        bookingRepository.save(transformRequestToObject(bookingRequest));
    }

    public Iterable<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public void updateBooking(Integer id, BookingRequest bookingRequest){
        try {
            Booking updatedBooking = transformRequestToObject(bookingRequest);
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

    public Booking transformRequestToObject(BookingRequest bookingRequest){
        Showing showing = showingRepository.findById(bookingRequest.getShowingId()).get();
        User user = userRepository.findById(bookingRequest.getUserId()).get();
        return new Booking(user, showing, new ArrayList<>(), bookingRequest.getPrice());
    }
}
