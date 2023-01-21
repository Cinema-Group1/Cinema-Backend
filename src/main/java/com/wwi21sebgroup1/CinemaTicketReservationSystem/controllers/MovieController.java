package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.NoSuchGenreException;
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
    @GetMapping("/all")
    public @ResponseBody Iterable<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/genre={genreName}")
    public ResponseEntity<Object> getMoviesByGenre(@PathVariable String genreName){
        try {
            return new ResponseEntity<>(movieService.getMoviesByGenre(genreName), HttpStatus.ACCEPTED);
        } catch (NoSuchGenreException noSuchGenreException) {
            return new ResponseEntity<>(noSuchGenreException.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update:{oldMovieId}")
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

    @DeleteMapping("/delete:{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable Integer id) {
        try {
            movieService.deleteMovie(id);
            return new ResponseEntity<>("Successfully deleted Movie with Id: " + id, HttpStatus.ACCEPTED);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }
}
