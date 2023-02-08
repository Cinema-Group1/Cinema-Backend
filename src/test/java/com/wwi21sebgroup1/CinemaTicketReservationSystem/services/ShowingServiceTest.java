package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.CinemaHall;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Showing;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.ShowingRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    @Mock
    private SeatingPlanService seatingPlanService;
    @InjectMocks
    private ShowingService showingService;

    private String title = "Trigger Point Showing";
    private String startsAt = "2023-02-07T20:00";
    private String endsAt = "2023-02-07T22:00";
    private int movieId = 1;
    private int cinemaHallId = 2;

    Movie movie = new Movie();
    CinemaHall cinemaHall = new CinemaHall();

    private ShowingRequest validRequest = new ShowingRequest(title, startsAt, endsAt, movieId, cinemaHallId, 6);
    private ShowingRequest invalidRequest = new ShowingRequest();
    private Showing showing = new Showing(title, LocalDateTime.parse(startsAt), LocalDateTime.parse(endsAt), movie, cinemaHall, 6);

    @Nested
    class AddShowing{
        Showing expected;
        Showing actual;
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = showing;
            ShowingService showingServiceSpy = spy(showingService);
            doReturn(showing).when(showingServiceSpy).processRequest(validRequest);
            actual = showingServiceSpy.addShowing(validRequest);
            assertEquals(expected, actual);
        }

        @Test
        public void t02InvalidRequest(){
            assertThrows(InvalidRequestException.class, () -> {
                ShowingService showingServiceSpy = spy(showingService);
                doThrow(new InvalidRequestException("ShowingRequest")).when(showingServiceSpy).processRequest(invalidRequest);
                showingServiceSpy.addShowing(invalidRequest);
            });
        }
    }
}
