package com.wwi21sebgroup1.CinemaTicketReservationSystem.integration;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers.ShowingController;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers.TicketController;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.SeatBookedException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.BookingRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookingProcessTest {

    @Autowired
    ShowingController showingController;
    @Autowired
    TicketController ticketController;
    @Autowired
    SeatingPlanTemplateRepository seatingPlanTemplateRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowingRepository showingRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    SeatingPlanRepository seatingPlanRepository;

    @Autowired
    TicketRepository ticketRepository;

    private Iterable<Ticket> expectedTickets;
    private Booking expectedBooking;
    private Iterable<Ticket> actualTickets;
    private Booking actualBooking;

    @Test
    public void t01BookingSuccessful(){
        SeatingPlanTemplate seatingPlanTemplate = seatingPlanTemplateRepository.findById(25).get();
        User user = userRepository.findById(40).get();
        Showing showing = showingRepository.findById(30).get();
        SeatingPlan seatingPlan = seatingPlanRepository.findByShowingId(showing.getId());
        expectedBooking = new Booking(user, showing, List.of(
                new Seat(10, true, seatingPlan, new SeatNumber('A', (byte)1, seatingPlanTemplate)),
                new Seat(10, true, seatingPlan, new SeatNumber('A', (byte)2, seatingPlanTemplate))
        ), 20);

        expectedTickets = List.of(new Ticket(showing, new SeatNumber('A', (byte)1, seatingPlanTemplate), expectedBooking),
                                  new Ticket(showing, new SeatNumber('A', (byte)2, seatingPlanTemplate), expectedBooking));

        ResponseEntity<Object> responseEntity =
                showingController.bookShowing(new BookingRequest(40, 30, List.of("A1", "A2")));
        actualBooking = (Booking)responseEntity.getBody();

        actualTickets = ticketController.getTicketsByBookingId(actualBooking.getId());

        assertEquals(expectedBooking, actualBooking);
        assertEquals(expectedTickets, actualTickets);
    }

    @Test
    public void t02SeatsAlreadyBooked(){
        ResponseEntity<Object> expected = new ResponseEntity<>(new SeatBookedException().toString(), HttpStatus.FORBIDDEN);
        ResponseEntity<Object> actual =
                showingController.bookShowing(new BookingRequest(40, 30, List.of("B1", "B2")));

        assertEquals(expected, actual);
    }
}
