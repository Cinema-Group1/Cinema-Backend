package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    @Mock
    private SeatingPlanRepository seatingPlanRepository;
    @Mock
    private SeatRepository seatRepository;
    @Mock
    private ShowingRepository showingRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private BookingService bookingService;

    @Test
    @DisplayName("BookingRequest to Booking: Transformation works as expected")
    public void transformRequestToObject(){
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        Integer showingId = 1;
        Showing showing = new Showing();
        showing.setId(showingId);
        SeatingPlan seatingPlan = new SeatingPlan();
        seatingPlan.setId(1);
        List<String> seatNumbersString = new ArrayList<>(Arrays.asList("A1", "A2", "A3"));
        List<Seat> seats = new ArrayList<>(Arrays.asList(   new Seat(10,false, seatingPlan, new SeatNumber('A', (byte)1, null)),
                                                            new Seat(10,false, seatingPlan, new SeatNumber('A', (byte)2, null)),
                                                            new Seat(10,false, seatingPlan, new SeatNumber('A', (byte)3, null))));
        BookingRequest bookingRequest = new BookingRequest(userId, showingId, seatNumbersString);
        when(showingRepository.findById(showingId)).thenReturn(Optional.of(showing));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(seatRepository.findAllBySeatingPlanId(1)).thenReturn(seats);
        when(seatingPlanRepository.findByShowingId(showingId)).thenReturn(seatingPlan);
        Booking actualBooking = bookingService.processRequest(bookingRequest);
        Booking expectedBooking = new Booking(user, showing, seats, 30);

        assertEquals(actualBooking, expectedBooking);
    }
}
