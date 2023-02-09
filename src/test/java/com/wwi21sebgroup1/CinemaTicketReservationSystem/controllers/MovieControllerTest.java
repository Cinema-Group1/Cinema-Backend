package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.NoSuchGenreException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.MovieRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.MovieService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {
    @Mock
    MovieService movieService;
    @InjectMocks
    MovieController movieController;
    static ResponseEntity<Object> expected;
    static ResponseEntity<Object> actual;
    String title = "Trigger Point";
    String imagePath = "test/path";
    String description = "Trigger";
    int length = 140;
    String releasedDate = "2015-12-12";
    String genreName = "Action";
    Genre genre = new Genre("Action", "Boom!");
    MovieRequest validRequest = new MovieRequest(title, imagePath, description, length, releasedDate, genreName);
    MovieRequest invalidRequest = new MovieRequest(title, imagePath, description, length, "", "");
    Movie movie = new Movie(title, imagePath, description, length, LocalDate.parse(releasedDate), genre);

    @Nested
    class AddMovie{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(movie, HttpStatus.ACCEPTED);
            when(movieService.addMovie(validRequest)).thenReturn(movie);
            actual = movieController.addMovie(validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>( new InvalidRequestException("MovieRequest").toString(),
                    HttpStatus.BAD_REQUEST);
            when(movieService.addMovie(invalidRequest)).thenThrow(new InvalidRequestException("MovieRequest"));
            actual = movieController.addMovie(invalidRequest);
            assertEquals(expected, actual);
        }
    }
    @Nested
    class GetAllMovies{
        @Test
        public void t01NoMoviesFound(){
            expected = new ResponseEntity<>(List.of(), HttpStatus.ACCEPTED);
            when(movieService.getAllMovies()).thenReturn(List.of());
            actual = movieController.getAllMovies();
            assertEquals(expected, actual);
        }
        @Test
        public void t02MovieFound(){
            expected = new ResponseEntity<>(List.of(movie), HttpStatus.ACCEPTED);
            when(movieService.getAllMovies()).thenReturn(List.of(movie));
            actual = movieController.getAllMovies();
            assertEquals(expected, actual);
        }
    }
    @Nested
    class getMoviesByGenre{
        ResponseEntity<Object> expected;
        ResponseEntity<Object> actual;
        @Test
        public void t01NoSuchGenre() throws NoSuchGenreException {
            expected = new ResponseEntity<>(new NoSuchGenreException(genreName).toString(), HttpStatus.NOT_FOUND);
            when(movieService.getMoviesByGenre(genreName)).thenThrow(new NoSuchGenreException(genreName));
            actual = movieController.getMoviesByGenre(genreName);
            assertEquals(expected, actual);
        }
        @Test
        public void t02NoMoviesFound() throws NoSuchGenreException {
            expected = new ResponseEntity<>(List.of(), HttpStatus.ACCEPTED);
            when(movieService.getMoviesByGenre(genreName)).thenReturn(List.of());
            actual = movieController.getMoviesByGenre(genreName);
            assertEquals(expected, actual);
        }
        @Test
        public void t03MovieFound() throws NoSuchGenreException {
            expected = new ResponseEntity<>(List.of(movie), HttpStatus.ACCEPTED);
            when(movieService.getMoviesByGenre(genreName)).thenReturn(List.of(movie));
            actual = new ResponseEntity<>(movieService.getMoviesByGenre(genreName), HttpStatus.ACCEPTED);
            assertEquals(expected, actual);
        }
    }
    @Nested
    class UpdateMovie{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(movie, HttpStatus.ACCEPTED);
            when(movieService.updateMovie(2, validRequest)).thenReturn(movie);
            actual = movieController.updateMovie(2, validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(new InvalidRequestException("MovieRequest").toString(), HttpStatus.BAD_REQUEST);
            when(movieService.updateMovie(1, invalidRequest)).thenThrow(new InvalidRequestException("MovieRequest"));
            actual = movieController.updateMovie(1, invalidRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t03GenreNotFound() throws InvalidRequestException {
            expected = new ResponseEntity<>(new NoSuchElementException().toString(), HttpStatus.NOT_FOUND);
            when(movieService.updateMovie(1, validRequest)).thenThrow(new NoSuchElementException());
            actual = movieController.updateMovie(1, validRequest);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class deleteMovie{
        @Test
        public void t01MovieFound(){
            expected = new ResponseEntity<>("Successfully deleted Movie with Id: " + 1, HttpStatus.ACCEPTED);
            actual = movieController.deleteMovie(1);
            assertEquals(expected, actual);
        }
        @Test
        public void t02MovieNotFound(){
            expected = new ResponseEntity<>(new NoSuchElementException().toString(), HttpStatus.NOT_FOUND);
            doThrow(new NoSuchElementException()).when(movieService).deleteMovie(10);
            actual = movieController.deleteMovie(10);
            assertEquals(expected, actual);
        }
    }
}
