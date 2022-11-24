package com.wwi21sebgroup1.CinemaTicketReservationSystem.movie;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    public List<Movie> getMovies() {
        return List.of(
                new Movie(
                        1L,
                        "Harry Potter",
                        150,
                        LocalDate.of(2010, 10, 20)
                )
        );
    }

}
