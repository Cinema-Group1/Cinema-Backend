package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.GenreRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    @InjectMocks
    GenreService genreService;

    Genre genre;
    GenreRequest genreRequest;

    public void setup(){
        String name = "Horror";
        String description = "Scary Stuff";
        genre = new Genre(name,description);
        genreRequest = new GenreRequest(name,description);
    }

    @Test
    @DisplayName("Transformation works as expected")
    public void t01TransformRequestToObject(){
        setup();

        Genre actualGenre = genreService.transformRequestToObject(genreRequest);
        Genre expectedGenre = genre;

        assertEquals(actualGenre,expectedGenre);
    }
}
