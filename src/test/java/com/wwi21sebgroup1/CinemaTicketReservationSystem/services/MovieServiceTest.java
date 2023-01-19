package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.MovieRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.MovieRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

//Attaches the Mockito Extension to the test class. See also https://www.baeldung.com/mockito-junit-5-extension
@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @Mock
    MovieRepository movieRepository;
    @Mock
    GenreRepository genreRepository;
    @InjectMocks
    MovieService movieService;
    Movie expected;
    Movie actual;
    String title = "Trigger Point";
    String imagePath = "test/path";
    String description = "Trigger";
    int length = 140;
    String releasedDate = "2015-12-12";
    String genreName = "Action";
    Genre genre = new Genre("Action", "Boom!");
    MovieRequest request;


    @Nested
    class AddMovie{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = new Movie(title, imagePath, description, length, LocalDate.parse(releasedDate), genre);
            request = new MovieRequest(title, imagePath, description, length, releasedDate, genreName);
            when(genreRepository.findByName(genreName)).thenReturn(Optional.of(genre));
            actual = movieService.addMovie(request);
        }
        @Test
        public void t02InvalidRequest(){
            request = new MovieRequest("", "", "", 0, "", "");
            try{
                movieService.addMovie(request);
                fail();
            }catch (InvalidRequestException invalidRequestException){}
        }

        @Test
        public void t03GenreNotFound() throws InvalidRequestException{
            try{
                MovieRequest request = new MovieRequest(title, imagePath, description, length, releasedDate, genreName);
                when(genreRepository.findByName(genreName)).thenThrow(new NoSuchElementException());
                movieService.addMovie(request);
                fail();
            }catch (NoSuchElementException noSuchElementException){}
        }
    }

    @Nested
    class ProcessRequest{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = new Movie(title, imagePath, description, length, LocalDate.parse(releasedDate), genre);
            request = new MovieRequest(title, imagePath, description, length, releasedDate, genreName);
            when(genreRepository.findByName(genreName)).thenReturn(Optional.of(genre));
            actual = movieService.processRequest(request);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest(){
            try{
                MovieRequest request = new MovieRequest("", imagePath, description, length, "", genreName);
                movieService.processRequest(request);
                fail();
            }catch(InvalidRequestException invalidRequestException){}
        }

        @Test
        public void t03GenreNotFound() throws InvalidRequestException{
            try{
                MovieRequest request = new MovieRequest(title, imagePath, description, length, releasedDate, genreName);
                when(genreRepository.findByName(genreName)).thenThrow(new NoSuchElementException());
                movieService.processRequest(request);
                fail();
            }catch (NoSuchElementException noSuchElementException){}
        }
    }
}
