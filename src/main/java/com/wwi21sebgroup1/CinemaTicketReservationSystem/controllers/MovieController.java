package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/movie")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @PutMapping("/add")
    public void addMovie(@RequestBody Movie movie){
        movieRepository.save(movie);
    }

    //returns all movies
    @GetMapping("/all")
    public @ResponseBody Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    //returns all movies from the specified genre
    @GetMapping("/genre={genre}")
    public @ResponseBody Iterable<Movie> getMoviesByGenre(@PathVariable String genre){
        return movieRepository.findByGenreName(genre);
    }
}
