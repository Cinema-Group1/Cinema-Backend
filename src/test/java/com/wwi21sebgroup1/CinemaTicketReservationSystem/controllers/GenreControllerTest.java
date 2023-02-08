package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.GenreRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.GenreService;
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
public class GenreControllerTest {
    @Mock
    GenreService genreService;
    @InjectMocks
    GenreController genreController;
    String genreName = "Action";
    Genre genre = new Genre(genreName, "Boom!");
    GenreRequest validRequest = new GenreRequest(genreName, "Boom!");
    GenreRequest invalidRequest = new GenreRequest();
    static ResponseEntity<Object> expected;
    static ResponseEntity<Object> actual;

    @Nested
    class AddGenre{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(genre, HttpStatus.ACCEPTED);
            when(genreService.addGenre(validRequest)).thenReturn(genre);
            actual = genreController.addGenre(validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>( new InvalidRequestException("GenreRequest").toString(), HttpStatus.BAD_REQUEST);
            when(genreService.addGenre(invalidRequest)).thenThrow(new InvalidRequestException("GenreRequest"));
            actual = genreController.addGenre(invalidRequest);
            assertEquals(expected, actual);
        }
    }
    @Nested
    class GetAllGenres{
        Iterable<Genre> expected;
        Iterable<Genre> actual;
        @Test
        public void t01NoGenresFound(){
            expected = List.of();
            when(genreService.getAllGenres()).thenReturn(List.of());
            actual = genreController.getAllGenres();
            assertEquals(expected, actual);
        }
        @Test
        public void t02GenreFound(){
            expected = List.of(genre);
            when(genreController.getAllGenres()).thenReturn(List.of(genre));
            actual = genreController.getAllGenres();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class UpdateGenre{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(genre, HttpStatus.ACCEPTED);
            when(genreService.updateGenre("Action", validRequest)).thenReturn(genre);
            actual = genreController.updateGenre("Action", validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(new InvalidRequestException("GenreRequest").toString(), HttpStatus.BAD_REQUEST);
            when(genreService.updateGenre("Action", invalidRequest)).thenThrow(new InvalidRequestException("GenreRequest"));
            actual = genreController.updateGenre("Action", invalidRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t03GenreNotFound() throws InvalidRequestException {
            expected = new ResponseEntity<>(new NoSuchElementException().toString(), HttpStatus.NOT_FOUND);
            when(genreService.updateGenre("Action", validRequest)).thenThrow(new NoSuchElementException());
            actual = genreController.updateGenre("Action", validRequest);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class deleteGenre{
        @Test
        public void t01GenreFound(){
            expected = new ResponseEntity<>("Successfully deleted genre " + genreName, HttpStatus.ACCEPTED);
            actual = genreController.deleteGenre("Action");
            assertEquals(expected, actual);
        }
        @Test
        public void t02GenreNotFound(){
            expected = new ResponseEntity<>(new NoSuchElementException().toString(), HttpStatus.NOT_FOUND);
            doThrow(new NoSuchElementException()).when(genreService).deleteGenre("Action");
            actual = genreController.deleteGenre("Action");
            assertEquals(expected, actual);
        }
    }
}
