package com.wwi21sebgroup1.CinemaTicketReservationSystem.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/movie")
public class MovieController {

    @Autowired
    private com.wwi21sebgroup1.CinemaTicketReservationSystem.movie.MovieRepository movieRepository;

    @GetMapping
    public @ResponseBody Iterable<Movie> getMovies() {
        return movieRepository.findAll();
    }

}
