package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.GenreRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {
    @Mock
    GenreRepository genreRepository;
    @InjectMocks
    GenreService genreService;
    String genreName = "Action";
    Genre genre = new Genre(genreName, "Boom!");
    GenreRequest validRequest = new GenreRequest(genreName, "Boom!");
    GenreRequest invalidRequest1 = new GenreRequest();
    GenreRequest invalidRequest2 = new GenreRequest(genreName, "");
    @Nested
    class AddGenre{
        Genre expected;
        Genre actual;
        @Test
        public void t01ValidRequest(){
            try{
                expected = genre;
                GenreService genreServiceSpy = spy(genreService);
                doReturn(genre).when(genreServiceSpy).processRequest(validRequest);
                actual = genreServiceSpy.addGenre(validRequest);
                assertEquals(expected, actual);
            }catch(InvalidRequestException invalidRequestException){
                fail(invalidRequestException);
            }
        }
        @Test
        public void t02InvalidRequest(){
            assertThrows(InvalidRequestException.class, () -> {
                GenreService genreServiceSpy = spy(genreService);
                doThrow(new InvalidRequestException("GenreRequest")).when(genreServiceSpy).processRequest(invalidRequest1);
                genreServiceSpy.addGenre(invalidRequest1);});
        }
    }

    @Nested
    class GetAllGenres{
        Iterable<Genre> expected;
        Iterable<Genre> actual;
        @Test
        public void t01NoGenresFound(){
            expected = List.of();
            when(genreRepository.findAll()).thenReturn(List.of());
            actual = genreService.getAllGenres();
            assertEquals(expected, actual);
        }
        @Test
        public void t02GenreFound(){
            expected = List.of(genre);
            when(genreRepository.findAll()).thenReturn(List.of(genre));
            actual = genreService.getAllGenres();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class UpdateGenre{
        Genre expected;
        Genre actual;
        @Test
        public void t01ValidRequest(){
            try{
                expected = genre;
                when(genreRepository.findByName(genreName)).thenReturn(Optional.of(genre));
                GenreService genreServiceSpy = spy(genreService);
                doReturn(genre).when(genreServiceSpy).processRequest(validRequest);
                actual = genreServiceSpy.updateGenre(genreName, validRequest);
                assertEquals(expected, actual);
            }catch(InvalidRequestException invalidRequestException){
                fail(invalidRequestException);
            }
        }
        @Test
        public void t02InvalidRequest(){
            assertThrows(InvalidRequestException.class, () ->{
                when(genreRepository.findByName(genreName)).thenReturn(Optional.of(genre));
                GenreService genreServiceSpy = spy(genreService);
                doThrow(new InvalidRequestException("GenreRequest")).when(genreServiceSpy).processRequest(invalidRequest1);
                genreServiceSpy.updateGenre("Action", invalidRequest1);
            });
        }
        @Test
        public void t03GenreFound(){
            try{
                expected = genre;
                when(genreRepository.findByName("Action")).thenReturn(Optional.of(genre));
                actual = genreService.updateGenre("Action", validRequest);
                assertEquals(expected, actual);
            }catch (InvalidRequestException invalidRequestException){
                fail(invalidRequestException);
            }
        }
        @Test
        public void t04GenreNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                when(genreRepository.findByName("Action")).thenReturn(Optional.empty());
                genreService.updateGenre("Action", validRequest);
            });
        }
    }

    @Nested
    class DeleteGenre{
        @Test
        public void t01GenreFound(){
            when(genreRepository.findByName("Action")).thenReturn(Optional.of(genre));
            genreService.deleteGenre("Action");
        }
        @Test
        public void t02GenreNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                doThrow(new NoSuchElementException()).when(genreRepository).findByName(genreName);
                genreService.deleteGenre(genreName);
            });
        }
    }

    @Nested
    class ProcessRequest{
        Genre expected;
        Genre actual;
        GenreRequest request;
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = genre;
            request = validRequest;
            actual = genreService.processRequest(request);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest(){
            assertThrows(InvalidRequestException.class, () -> {
                request = invalidRequest1;
                genreService.processRequest(invalidRequest1);
            });
        }
        @Test

        public void t03InvalidRequest(){
            assertThrows(InvalidRequestException.class, () -> {
                request = invalidRequest2;
                genreService.processRequest(invalidRequest2);
            });
        }
    }
}
