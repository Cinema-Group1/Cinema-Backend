package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    @Mock
    private SeatingPlanRepository seatingPlanRepository;
    @Mock
    private SeatRepository seatRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private BookingService bookingService;

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
        Booking expected;
        Booking actual;
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = booking;
            BookingService bookingServiceSpy = spy(bookingService);
            doReturn(booking).when(bookingServiceSpy).processRequest(validRequest);
            actual = bookingServiceSpy.addBooking(validRequest);
            assertEquals(expected, actual);
        }

        @Test
        public void t02InvalidRequest(){
            assertThrows(InvalidRequestException.class, () -> {
                BookingService bookingServiceSpy = spy(bookingService);
                doThrow(new InvalidRequestException("BookingRequest")).when(bookingServiceSpy).processRequest(invalidRequest);
                bookingServiceSpy.addBooking(invalidRequest);
            });
        }
    }

    @Nested
    class GetAllBookings{
        Iterable<Booking> expected;
        Iterable<Booking> actual;
        @Test
        public void t01NoBookingsFound(){
            expected = List.of();
            when(bookingRepository.findAll()).thenReturn(List.of());
            actual = bookingService.getAllBookings();
            assertEquals(expected, actual);
        }
        @Test
        public void t02BookingFound(){
            expected = List.of(booking);
            when(bookingRepository.findAll()).thenReturn(List.of(booking));
            actual = bookingService.getAllBookings();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetBookingsByUserId{
        Iterable<Booking> expected;
        Iterable<Booking> actual;
        @Test
        public void t01NoSuchUser(){
            when(bookingRepository.findByUserId(userId)).thenThrow(new NoSuchElementException());
            assertThrows(NoSuchElementException.class, () -> bookingService.getBookingsByUserId(userId));
        }
        @Test
        public void t02NoBookingsFound(){
            expected = List.of();
            when(bookingRepository.findByUserId(userId)).thenReturn(List.of());
            actual = bookingService.getBookingsByUserId(userId);
            assertEquals(expected, actual);
        }
        @Test
        public void t03BookingFound(){
            expected = List.of(booking);
            when(bookingRepository.findByUserId(userId)).thenReturn(List.of(booking));
            actual = bookingService.getBookingsByUserId(userId);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class UpdateBooking{
        Booking expected;
        Booking actual;
        @Test
        public void t01BookingFound() throws InvalidRequestException {
            expected = booking;
            when(bookingRepository.findById(123)).thenReturn(Optional.of(booking));
            BookingService bookingServiceSpy = spy(bookingService);
            doReturn(booking).when(bookingServiceSpy).processRequest(validRequest);
            actual = bookingServiceSpy.updateBooking(123, validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02BookingNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                when(bookingRepository.findById(3)).thenThrow(new NoSuchElementException());
                bookingService.updateBooking(3, validRequest);
            });
        }
        @Test
        public void t03InvalidRequest() throws InvalidRequestException {
            BookingService bookingServiceSpy = spy(bookingService);
            doThrow(new InvalidRequestException("BookingRequest")).when(bookingServiceSpy).processRequest(invalidRequest);
            assertThrows(InvalidRequestException.class, () -> bookingServiceSpy.updateBooking(1, invalidRequest));
        }
    }

    @Nested
    class DeleteBooking{
        @Test
        public void t01BookingFound(){
            bookingService.deleteBooking(1);
        }
        @Test
        public void t02BookingNotFound(){
            doThrow(new NoSuchElementException()).when(bookingRepository).deleteById(1);
            assertThrows(NoSuchElementException.class, () -> bookingService.deleteBooking(1));
        }
    }
}
