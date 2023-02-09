package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.NoSuchGenreException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.MovieRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.MovieRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    MovieRequest request = new MovieRequest(title, imagePath, description, length, releasedDate, genreName);
    MovieRequest invalidRequest = new MovieRequest("", "", "", 0, "", "");
    Movie movie = new Movie(title, imagePath, description, length, LocalDate.parse(releasedDate), genre);


    @Nested
    class AddMovie{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = movie;
            when(genreRepository.findByName(genreName)).thenReturn(Optional.of(genre));
            actual = movieService.addMovie(request);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest(){
            assertThrows(InvalidRequestException.class, () -> movieService.addMovie(invalidRequest));
        }

        @Test
        public void t03GenreNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                MovieRequest request = new MovieRequest(title, imagePath, description, length, releasedDate, genreName);
                when(genreRepository.findByName(genreName)).thenThrow(new NoSuchElementException());
                movieService.addMovie(request);
            });
        }
    }

    @Nested
    class GetAllMovies{
        Iterable<Movie> expected;
        Iterable<Movie> actual;
        @Test
        public void t01NoMoviesFound(){
            expected = List.of();
            when(movieRepository.findAll()).thenReturn(List.of());
            actual = movieService.getAllMovies();
            assertEquals(expected, actual);
        }
        @Test
        public void t02MovieFound(){
            expected = List.of(movie);
            when(movieRepository.findAll()).thenReturn(List.of(movie));
            actual = movieService.getAllMovies();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetMoviesByGenre{
        Iterable<Movie> expected;
        Iterable<Movie> actual;
        @Test
        public void t01NoSuchGenre(){
            assertThrows(NoSuchGenreException.class, () -> {
                when(genreRepository.findByName(genreName)).thenReturn(Optional.empty());
                movieService.getMoviesByGenre(genreName);
            });
        }
        @Test
        public void t02NoMoviesFound() throws NoSuchGenreException {
            when(genreRepository.findByName(genreName)).thenReturn(Optional.of(genre));
            expected = List.of();
            when(movieRepository.findByGenreName(genreName)).thenReturn(List.of());
            actual = movieService.getMoviesByGenre(genreName);
            assertEquals(expected, actual);
        }
        @Test
        public void t03MoviesFound() throws NoSuchGenreException {
            when(genreRepository.findByName(genreName)).thenReturn(Optional.of(genre));
            expected = List.of(movie);
            when(movieRepository.findByGenreName(genreName)).thenReturn(List.of(movie));
            actual = movieService.getMoviesByGenre(genreName);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class UpdateMovie{
        @Test
        public void t01MovieFound() throws InvalidRequestException {
            expected = movie;
            when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
            MovieService movieServiceSpy = spy(movieService);
            doReturn(movie).when(movieServiceSpy).processRequest(request);
            actual = movieServiceSpy.updateMovie(1, request);
            assertEquals(expected, actual);
        }
        @Test
        public void t02MovieNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                when(movieRepository.findById(3)).thenThrow(new NoSuchElementException());
                movieService.updateMovie(3, request);
            });
        }
        @Test
        public void t03InvalidRequest() throws InvalidRequestException {
            MovieService movieServiceSpy = spy(movieService);
            doThrow(new InvalidRequestException("MovieRequest")).when(movieServiceSpy).processRequest(invalidRequest);
            assertThrows(InvalidRequestException.class, () -> movieServiceSpy.updateMovie(1, invalidRequest));
        }
    }

    @Nested
    class DeleteMovie{
        @Test
        public void t01MovieFound(){
            movieService.deleteMovie(1);
        }
        @Test
        public void t02MovieNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                //doThrow syntax is required because deleteById has return type void
                doThrow(new NoSuchElementException()).when(movieRepository).deleteById(1);
                movieService.deleteMovie(1);
            });
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
            assertThrows(InvalidRequestException.class, () -> movieService.processRequest(invalidRequest));
        }

        @Test
        public void t03GenreNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                when(genreRepository.findByName(genreName)).thenThrow(new NoSuchElementException());
                movieService.processRequest(request);
            });
        }
    }
}
