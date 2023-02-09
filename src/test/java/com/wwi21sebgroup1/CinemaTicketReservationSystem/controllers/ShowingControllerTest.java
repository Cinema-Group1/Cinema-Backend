package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.CinemaHall;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Showing;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.ShowingRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.ShowingService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShowingControllerTest {
    @Mock
    ShowingService showingService;
    @InjectMocks
    ShowingController showingController;
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

    ResponseEntity<Object> expected;
    ResponseEntity<Object> actual;

    @Nested
    class AddShowing{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(showing, HttpStatus.ACCEPTED);
            when(showingService.addShowing(validRequest)).thenReturn(showing);
            actual = showingController.addShowing(validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>( new InvalidRequestException("ShowingRequest").toString(),
                    HttpStatus.BAD_REQUEST);
            when(showingService.addShowing(invalidRequest)).thenThrow(new InvalidRequestException("ShowingRequest"));
            actual = showingController.addShowing(invalidRequest);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetAllShowings{
        @Test
        public void t01NoShowingsFound(){
            expected = new ResponseEntity<>(List.of(), HttpStatus.ACCEPTED);
            when(showingService.getAllShowings()).thenReturn(List.of());
            actual = showingController.getAllShowings();
            assertEquals(expected, actual);
        }
        @Test
        public void t02ShowingFound(){
            expected = new ResponseEntity<>(List.of(showing), HttpStatus.ACCEPTED);
            when(showingService.getAllShowings()).thenReturn(List.of(showing));
            actual = showingController.getAllShowings();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetAllShowingsInFuture{
        @Test
        public void t01NoShowingsFound(){
            expected = new ResponseEntity<>(List.of(), HttpStatus.ACCEPTED);
            when(showingService.getAllShowingsInFuture()).thenReturn(List.of());
            actual = showingController.getAllShowingsInFuture();
            assertEquals(expected, actual);
        }
        @Test
        public void t02ShowingFound(){
            expected = new ResponseEntity<>(List.of(showing), HttpStatus.ACCEPTED);
            when(showingService.getAllShowingsInFuture()).thenReturn(List.of(showing));
            actual = showingController.getAllShowingsInFuture();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class getShowingsByMovie{
        @Test
        public void t01NoShowingsFound(){
            expected = new ResponseEntity<>(List.of(), HttpStatus.ACCEPTED);
            when(showingService.getShowingsByMovieId(movieId)).thenReturn(List.of());
            actual = showingController.getShowingsByMovie(movieId);
            assertEquals(expected, actual);
        }
        @Test
        public void t02ShowingFound(){
            expected = new ResponseEntity<>(List.of(showing), HttpStatus.ACCEPTED);
            when(showingService.getShowingsByMovieId(movieId)).thenReturn(List.of(showing));
            actual = showingController.getShowingsByMovie(movieId);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class UpdateShowing{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(showing, HttpStatus.ACCEPTED);
            when(showingService.updateShowing(2, validRequest)).thenReturn(showing);
            actual = showingController.updateShowing(2, validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(new InvalidRequestException("ShowingRequest").toString(), HttpStatus.BAD_REQUEST);
            when(showingService.updateShowing(1, invalidRequest)).thenThrow(new InvalidRequestException("ShowingRequest"));
            actual = showingController.updateShowing(1, invalidRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t03GenreNotFound() throws InvalidRequestException {
            expected = new ResponseEntity<>(new NoSuchElementException().toString(), HttpStatus.NOT_FOUND);
            when(showingService.updateShowing(1, validRequest)).thenThrow(new NoSuchElementException());
            actual = showingController.updateShowing(1, validRequest);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class deleteShowing{
        @Test
        public void t01ShowingFound(){
            expected = new ResponseEntity<>("Successfully deleted showing with Id: " + 1, HttpStatus.ACCEPTED);
            actual = showingController.deleteShowing(1);
            assertEquals(expected, actual);
        }
        @Test
        public void t02ShowingNotFound(){
            expected = new ResponseEntity<>(new NoSuchElementException().toString(), HttpStatus.NOT_FOUND);
            doThrow(new NoSuchElementException()).when(showingService).deleteShowing(10);
            actual = showingController.deleteShowing(10);
            assertEquals(expected, actual);
        }
    }
}
