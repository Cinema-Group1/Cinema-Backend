package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ShowingServiceTest {
    @Mock
    private ShowingRepository showingRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CinemaHallRepository cinemaHallRepository;
    @Mock
    private SeatingPlanRepository seatingPlanRepository;
    @Mock
    private SeatRepository seatRepository;
    @InjectMocks
    private MovieService movieService;

    @Test
    public void t01Book(){

    }
}
