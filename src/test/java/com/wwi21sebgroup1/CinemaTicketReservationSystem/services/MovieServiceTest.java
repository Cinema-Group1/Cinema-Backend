package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.MovieRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.MovieRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

//Attaches the Mockito Extension to the test class. See also https://www.baeldung.com/mockito-junit-5-extension
@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private GenreRepository genreRepository;
    @InjectMocks
    MovieService movieService;

    MovieRequest movieRequest;
    Movie movie;
    Integer id;
    Genre genre;

    public void setup(){
        String title = "Test Movie";
        int length = 135;
        String imagePath = "/test/path";
        String description = "Test description";
        String releasedDate = "2020-07-12";
        String genreName = "Test Genre";
        id = 1;
        genre = new Genre("Test Genre", "Test description");
        movieRequest = new MovieRequest(title, imagePath, description, length, releasedDate, genreName);
        movie = new Movie(title, imagePath, description, length, Date.valueOf(releasedDate), genre);
    }

    @Test
    @DisplayName("Transformation works as expected")
    public void t01TransformRequestToObject(){
        setup();
        when(genreRepository.findByName(movieRequest.getGenreName())).thenReturn(Optional.of(genre));

        Movie actualMovie = movieService.transformRequestToObject(movieRequest);
        actualMovie.setId(id);

        Movie expectedMovie = movie;
        expectedMovie.setId(id);

        assertEquals(actualMovie, expectedMovie);
    }

    @Test
    @DisplayName("Genre was not found")
    public void t02TransformRequestToObject(){
        assertThrows(NoSuchElementException.class, () -> {
            setup();

            Movie actualMovie = movieService.transformRequestToObject(movieRequest);
            actualMovie.setId(id);

            Movie expectedMovie = movie;
            expectedMovie.setId(id);
        });
    }
}
