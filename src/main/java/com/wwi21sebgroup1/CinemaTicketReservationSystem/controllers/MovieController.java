package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.MovieRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PutMapping("/add")
    public ResponseEntity<Object> addMovie(@RequestBody MovieRequest movieRequest){
        try {
            return new ResponseEntity<>(movieService.addMovie(movieRequest), HttpStatus.ACCEPTED);
        }catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update:{oldMovieId}")
    public ResponseEntity<Object> updateMovie(@PathVariable Integer oldMovieId, @RequestBody MovieRequest movieRequest){
        try {
            return new ResponseEntity<>(movieService.updateMovie(oldMovieId, movieRequest), HttpStatus.ACCEPTED);
        }
        catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }
        catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/delete:{movieId}")
    public void deleteMovie(@PathVariable Integer movieId) {
        movieService.deleteMovie(movieId);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/genre={genreName}")
    public @ResponseBody Iterable<Movie> getMoviesByGenre(@PathVariable String genreName){
        return movieService.getMoviesByGenre(genreName);
    }
}
