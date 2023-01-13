package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.ShowingRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SeatServiceTest {
    @Mock
    private SeatNumberRepository seatNumberRepository;
    @Mock
    private ShowingRepository showingRepository;
    @Mock
    private SeatingPlanRepository seatingPlanRepository;
    @InjectMocks
    SeatService seatService;

    SeatNumber seatNumber;
    Showing showing;
    SeatingPlan seatingPlan;
    Seat seat;
    SeatRequest seatRequest;
    int id;

    public void setup(){
        SeatingPlanTemplate seatingPlanTemplate = new SeatingPlanTemplate();
        seatNumber = new SeatNumber('Z',(byte)1, seatingPlanTemplate);
        seatNumber.setId(1);
        showing = new Showing();
        showing.setId(1);
        seatingPlan = new SeatingPlan();
        seatingPlan.setId(1);
        showing.setSeatingPlan(seatingPlan);
        int price = 15;
        boolean occupied = false;
        seat = new Seat(price, occupied, seatingPlan, seatNumber);
        seatRequest = new SeatRequest(price, occupied, seatNumber.getId(), 1);
        id = 1;
    }
    @Test
    @DisplayName("SeatRequest to Seat: Transformation works as expected")
    public void transformRequestToObject() {
        setup();
        when(seatNumberRepository.findById(seatRequest.getSeatNumberId())).thenReturn(Optional.of(seatNumber));
        when(showingRepository.findById(seatRequest.getShowingId())).thenReturn(Optional.of(showing));
        when(seatingPlanRepository.findById(showing.getSeatingPlan().getId())).thenReturn(Optional.of(seatingPlan));

        Seat actualSeat = seatService.transformRequestToObject(seatRequest);
        actualSeat.setId(id);

        Seat expectedSeat = seat;
        seat.setId(id);

        assertEquals(actualSeat,expectedSeat);
    }
}
