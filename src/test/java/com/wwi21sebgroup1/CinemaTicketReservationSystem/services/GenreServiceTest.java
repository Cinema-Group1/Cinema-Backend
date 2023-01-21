package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.GenreRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
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
    Genre expected;
    Genre actual;
    static String genreName1 = "Action";
    static String genreDescription1 = "Boom!";
    static String genreName2 = "Horror";
    static String genreDescription2 = "Scary!";
    static GenreRequest validRequest = new GenreRequest(genreName1, genreDescription1);
    static GenreRequest invalidRequest = new GenreRequest(" ", "  ");
    static Genre genre1 = new Genre(genreName1, genreDescription1);
    static Genre genre2 = new Genre(genreName2, genreDescription2);
    static Iterable<Genre> genres = new ArrayList<>();

    @Nested
    class AddGenre{
        @Test
        public void t01ValidRequest(){
            try{
                expected = genre1;
                GenreService genreServiceSpy = spy(genreService);
                doReturn(genre1).when(genreServiceSpy).processRequest(validRequest);
                actual = genreServiceSpy.addGenre(validRequest);
                assertEquals(expected, actual);
            }catch(InvalidRequestException invalidRequestException){
                fail(invalidRequestException);
            }
        }
        @Test
        public void t02InvalidRequest(){
            try{
                GenreService genreServiceSpy = spy(genreService);
                doThrow(new InvalidRequestException("GenreRequest")).when(genreServiceSpy).processRequest(invalidRequest);
                genreServiceSpy.addGenre(invalidRequest);
                fail("InvalidRequestException was not thrown!");
            }catch(InvalidRequestException invalidRequestException){}
        }
    }

    @Nested
    class UpdateGenre{
        @Test
        public void t01ValidRequest(){
            try{
                expected = genre1;
                when(genreRepository.findByName(genreName1)).thenReturn(Optional.of(genre1));
                GenreService genreServiceSpy = spy(genreService);
                doReturn(genre1).when(genreServiceSpy).processRequest(validRequest);
                actual = genreServiceSpy.updateGenre(genreName1, validRequest);
                assertEquals(expected, actual);
            }catch(InvalidRequestException invalidRequestException){
                fail(invalidRequestException);
            }
        }
        @Test
        public void t02InvalidRequest(){
            try{
                when(genreRepository.findByName(genreName1)).thenReturn(Optional.of(genre1));
                GenreService genreServiceSpy = spy(genreService);
                doThrow(new InvalidRequestException("GenreRequest")).when(genreServiceSpy).processRequest(invalidRequest);
                genreServiceSpy.updateGenre("Action", invalidRequest);
                fail("InvalidRequestException was not thrown!");
            }catch(InvalidRequestException invalidRequestException){}
        }
        @Test
        public void t03GenreFound(){
            try{
                expected = genre1;
                when(genreRepository.findByName("Action")).thenReturn(Optional.of(genre1));
                actual = genreService.updateGenre("Action", validRequest);
                assertEquals(expected, actual);
            }catch (InvalidRequestException invalidRequestException){
                fail(invalidRequestException);
            }
        }
        @Test
        public void t04GenreNotFound(){
            try {
                when(genreRepository.findByName("Action")).thenReturn(Optional.empty());
                genreService.updateGenre("Action", validRequest);
                fail("NoSuchElementException was not thrown!");
            }catch(NoSuchElementException noSuchElementException){}
            catch (Exception exception){
                fail(exception);
            }
        }
    }
}
