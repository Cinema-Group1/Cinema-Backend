package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.BookingService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {
    @Mock
    BookingService bookingService;
    @InjectMocks
    BookingController bookingController;
    ResponseEntity<Object> expected;
    ResponseEntity<Object> actual;

    int userId = 1;
    int showingId = 2;
    List<String> seatNumbers = List.of("A1", "A2");
    BookingRequest validRequest = new BookingRequest(userId, showingId, seatNumbers);
    BookingRequest invalidRequest = new BookingRequest();

    User user = new User();
    Showing showing = new Showing();
    SeatingPlan seatingPlan = new SeatingPlan();
    List<Seat> seats = List.of(new Seat(6, false, seatingPlan, new SeatNumber()),
                                new Seat(6, false, seatingPlan, new SeatNumber()));
    Booking booking = new Booking(user, showing, seats, 12);

    @Nested
    class AddBooking{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(booking, HttpStatus.ACCEPTED);
            when(bookingService.addBooking(validRequest)).thenReturn(booking);
            actual = bookingController.addBooking(validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>( new InvalidRequestException("BookingRequest").toString(),
                    HttpStatus.BAD_REQUEST);
            when(bookingService.addBooking(invalidRequest)).thenThrow(new InvalidRequestException("BookingRequest"));
            actual = bookingController.addBooking(invalidRequest);
            assertEquals(expected, actual);
        }
    }
    @Nested
    class GetAllBookings{
        @Test
        public void t01NoBookingsFound(){
            expected = new ResponseEntity<>(List.of(), HttpStatus.ACCEPTED);
            when(bookingService.getAllBookings()).thenReturn(List.of());
            actual = bookingController.getAllBookings();
            assertEquals(expected, actual);
        }
        @Test
        public void t02BookingFound(){
            expected = new ResponseEntity<>(List.of(booking), HttpStatus.ACCEPTED);
            when(bookingService.getAllBookings()).thenReturn(List.of(booking));
            actual = bookingController.getAllBookings();
            assertEquals(expected, actual);
        }
    }
    @Nested
    class getBookingsByUserId{
        @Test
        public void t01NoSuchUser() throws NoSuchElementException {
            expected = new ResponseEntity<>(new NoSuchElementException().toString(), HttpStatus.NOT_FOUND);
            when(bookingService.getBookingsByUserId(userId)).thenThrow(new NoSuchElementException());
            actual = bookingController.getBookingsByUserId(userId);
            assertEquals(expected, actual);
        }
        @Test
        public void t02NoBookingsFound() throws NoSuchElementException {
            expected = new ResponseEntity<>(List.of(), HttpStatus.ACCEPTED);
            when(bookingService.getBookingsByUserId(userId)).thenReturn(List.of());
            actual = bookingController.getBookingsByUserId(userId);
            assertEquals(expected, actual);
        }
        @Test
        public void t03BookingFound() throws NoSuchElementException {
            expected = new ResponseEntity<>(List.of(booking), HttpStatus.ACCEPTED);
            when(bookingService.getBookingsByUserId(userId)).thenReturn(List.of(booking));
            actual = new ResponseEntity<>(bookingService.getBookingsByUserId(userId), HttpStatus.ACCEPTED);
            assertEquals(expected, actual);
        }
    }
    @Nested
    class UpdateBooking{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(booking, HttpStatus.ACCEPTED);
            when(bookingService.updateBooking(2, validRequest)).thenReturn(booking);
            actual = bookingController.updateBooking(2, validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(new InvalidRequestException("BookingRequest").toString(), HttpStatus.BAD_REQUEST);
            when(bookingService.updateBooking(1, invalidRequest)).thenThrow(new InvalidRequestException("BookingRequest"));
            actual = bookingController.updateBooking(1, invalidRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t03GenreNotFound() throws InvalidRequestException {
            expected = new ResponseEntity<>(new NoSuchElementException().toString(), HttpStatus.NOT_FOUND);
            when(bookingService.updateBooking(1, validRequest)).thenThrow(new NoSuchElementException());
            actual = bookingController.updateBooking(1, validRequest);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class deleteBooking{
        @Test
        public void t01BookingFound(){
            expected = new ResponseEntity<>("Successfully deleted booking with Id: " + 1, HttpStatus.ACCEPTED);
            actual = bookingController.deleteBooking(1);
            assertEquals(expected, actual);
        }
        @Test
        public void t02BookingNotFound(){
            expected = new ResponseEntity<>(new NoSuchElementException().toString(), HttpStatus.NOT_FOUND);
            doThrow(new NoSuchElementException()).when(bookingService).deleteBooking(10);
            actual = bookingController.deleteBooking(10);
            assertEquals(expected, actual);
        }
    }
}
