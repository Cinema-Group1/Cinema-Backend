package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.MovieRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/movie")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;

    @PostMapping("/add")
    public void addMovie(@RequestBody MovieRequest movieRequest){
        Genre genre = genreRepository.findByName(movieRequest.getGenreName());
        movieRepository.save(transformRequestToObject(movieRequest));
    }

    @PostMapping("/update:{oldMovieId}")
    public void updateMovie(@PathVariable Integer oldMovieId, @RequestBody MovieRequest movieRequest){
        try{
            Movie updatedMovie = transformRequestToObject(movieRequest);
            updatedMovie.setId(oldMovieId);
            movieRepository.save(updatedMovie);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    @PostMapping("/delete:{oldMovieId}")
    public void deleteMovie(@PathVariable Integer oldMovieId) {
        try {
            movieRepository.deleteById(oldMovieId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Movie transformRequestToObject(MovieRequest movieRequest){
        Genre genre = genreRepository.findByName(movieRequest.getGenreName());
        return new Movie( movieRequest.getTitle(),
                movieRequest.getLength(),
                new SimpleDateFormat(movieRequest.getReleasedDateString()),
                genre);
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
