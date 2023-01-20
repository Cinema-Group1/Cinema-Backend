package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.NoSuchGenreException;
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
    Movie movie = new Movie(title, imagePath, description, length, LocalDate.parse(releasedDate), genre);


    @Nested
    class AddMovie{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = movie;
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
    class getMoviesByGenre{
        Iterable<Movie> expected;
        Iterable<Movie> actual;
        @Test
        public void t01NoSuchGenre(){
            when(genreRepository.findByName(genreName)).thenReturn(Optional.empty());
            try{
                movieService.getMoviesByGenre(genreName);
                fail();
            }catch(NoSuchGenreException noSuchGenreException){}
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
        public void t03NoMoviesFound() throws NoSuchGenreException {
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
            try {
                when(movieRepository.findById(3)).thenThrow(new NoSuchElementException());
                movieService.updateMovie(3, request);
                fail();
            }catch(NoSuchElementException noSuchElementException){}
            catch(Exception exception){
                fail();
            }
        }
        @Test
        public void t03ValidRequest() throws InvalidRequestException {
            expected = movie;
            when(genreRepository.findByName(genreName)).thenReturn(Optional.of(genre));
            actual = movieService.updateMovie(1, request);
        }
        @Test
        public void t04InvalidRequest(){
            request = new MovieRequest("", "", "", 0, "", "");
            try{
                movieService.updateMovie(1, request);
                fail();
            }catch (InvalidRequestException invalidRequestException){}
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
            try {
                //doThrow syntax is required because deleteById has return type void
                doThrow(new NoSuchElementException()).when(movieRepository).deleteById(1);
                movieService.deleteMovie(1);
                fail();
            }catch(NoSuchElementException noSuchElementException){}
            catch(Exception exception){
                fail();
            }
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
