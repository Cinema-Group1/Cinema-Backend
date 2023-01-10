package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.MovieRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PutMapping("/add")
    public void addMovie(@RequestBody MovieRequest movieRequest){
        movieService.addMovie(movieRequest);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    //returns all movies from the specified genre
    @GetMapping("/genre={genre}")
    public @ResponseBody Iterable<Movie> getMoviesByGenre(@PathVariable String genreName){
        return movieService.getMoviesByGenre(genreName);
    }

    @PostMapping("/update:{oldMovieId}")
    public void updateMovie(@PathVariable Integer oldMovieId, @RequestBody MovieRequest movieRequest){
        movieService.updateMovie(oldMovieId, movieRequest);
    }

    @PostMapping("/delete:{movieId}")
    public void deleteMovie(@PathVariable Integer movieId) {
        movieService.deleteMovie(movieId);
    }
}
