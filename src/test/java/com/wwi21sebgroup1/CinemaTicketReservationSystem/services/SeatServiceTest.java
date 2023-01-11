package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
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
    @InjectMocks
    SeatService seatService;

    SeatNumber seatNumber;
    Seat seat;
    SeatRequest seatRequest;
    int id;

    public void setup(){
        seatNumber = new SeatNumber('Z',(byte)1, null);
        seatNumber.setId(1);
        int price = 15;
        boolean occupied = false;
        seat = new Seat(price, occupied, null, seatNumber);
        seatRequest = new SeatRequest(occupied, seatNumber.getId(), 0);
        id = 1;
    }
    @Test
    @DisplayName("SeatRequest to Seat: Transformation works as expected\"")
    public void t01TransformRequestToObject() {
        setup();
        when(seatNumberRepository.findById(seatRequest.getSeatNumberId())).thenReturn(Optional.of(seatNumber));

        Seat actualSeat = seatService.transformRequestToObject(seatRequest);
        actualSeat.setId(id);

        Seat expectedSeat = seat;
        seat.setId(id);

        assertEquals(actualSeat,expectedSeat);
    }
    @Test
    @DisplayName("SeatRequest to Seat: Non-Existing Address throws correct Exception")
    public void t02TransformRequestToObject(){
        assertThrows(NoSuchElementException.class, () -> {
            setup();

            Seat actualSeat = seatService.transformRequestToObject(seatRequest);
            actualSeat.setId(id);

            Seat expectedSeat = seat;
            seat.setId(id);
        });
    }
}
