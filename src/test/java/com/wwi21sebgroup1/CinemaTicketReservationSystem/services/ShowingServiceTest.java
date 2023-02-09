package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Nested
    class GetAllShowings{
        Iterable<Showing> expected;
        Iterable<Showing> actual;
        @Test
        public void t01NoShowingsFound(){
            expected = List.of();
            when(showingRepository.findAll()).thenReturn(List.of());
            actual = showingService.getAllShowings();
            assertEquals(expected, actual);
        }
        @Test
        public void t02ShowingFound(){
            expected = List.of(showing);
            when(showingRepository.findAll()).thenReturn(List.of(showing));
            actual = showingService.getAllShowings();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetAllShowingsInFuture{
        Iterable<Showing> expected;
        Iterable<Showing> actual;
        @Test
        public void t01NoShowingsFound(){
            expected = List.of();
            when(showingRepository.findAll()).thenReturn(List.of());
            actual = showingService.getAllShowings();
            assertEquals(expected, actual);
        }

        @Test
        public void t02NoShowingsInFuture(){
            expected = List.of();
            when(showingRepository.findAll()).thenReturn(List.of(showing));
            actual = showingService.getAllShowingsInFuture();
            assertEquals(expected, actual);
        }

        @Test
        public void t03ShowingsInFuture(){
            Showing showingInFuture = new Showing(  title,
                                                    LocalDateTime.parse("9999-01-01T20:00"),
                                                    LocalDateTime.parse("9999-01-01T22:00"),
                                                    movie,
                                                    cinemaHall,
                                                    6);
            expected = List.of(showingInFuture);
            when(showingRepository.findAll()).thenReturn(List.of(showing, showingInFuture));
            actual = showingService.getAllShowingsInFuture();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetShowingsByMovieId{
        Iterable<Showing> expected;
        Iterable<Showing> actual;
        @Test
        public void t01NoShowingsFound(){
            expected = List.of();
            when(showingRepository.findByMovieId(1)).thenReturn(List.of());
            actual = showingService.getShowingsByMovieId(1);
            assertEquals(expected, actual);
        }
        @Test
        public void t02ShowingFound(){
            expected = List.of(showing);
            when(showingRepository.findByMovieId(1)).thenReturn(List.of(showing));
            actual = showingService.getShowingsByMovieId(1);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class UpdateShowing{
        Showing expected;
        Showing actual;
        @Test
        public void t01ShowingFound() throws InvalidRequestException {
            expected = showing;
            when(showingRepository.findById(1)).thenReturn(Optional.of(showing));
            ShowingService showingServiceSpy = spy(showingService);
            doReturn(showing).when(showingServiceSpy).processRequest(validRequest);
            actual = showingServiceSpy.updateShowing(1, validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02ShowingNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                when(showingRepository.findById(3)).thenThrow(new NoSuchElementException());
                showingService.updateShowing(3, validRequest);
            });
        }
        @Test
        public void t03InvalidRequest() throws InvalidRequestException {
            ShowingService showingServiceSpy = spy(showingService);
            doThrow(new InvalidRequestException("ShowingRequest")).when(showingServiceSpy).processRequest(invalidRequest);
            assertThrows(InvalidRequestException.class, () -> showingServiceSpy.updateShowing(1, invalidRequest));
        }
    }

    @Nested
    class ProcessRequest{
        Showing expected;
        Showing actual;
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = showing;
            when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
            when(cinemaHallRepository.findById(cinemaHallId)).thenReturn(Optional.of(cinemaHall));
            actual = showingService.processRequest(validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest(){
            assertThrows(InvalidRequestException.class, () -> showingService.processRequest(invalidRequest));
        }

        @Test
        public void t03MovieNotFound(){
            when(movieRepository.findById(movieId)).thenThrow(new NoSuchElementException());
            assertThrows(NoSuchElementException.class, () -> showingService.processRequest(validRequest));
        }

        @Test
        public void t04CinemaHallNotFound(){
            when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
            when(cinemaHallRepository.findById(cinemaHallId)).thenThrow(new NoSuchElementException());
            assertThrows(NoSuchElementException.class, () -> showingService.processRequest(validRequest));
        }
    }
}
