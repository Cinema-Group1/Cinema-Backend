package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/movie")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;

    @PutMapping("/add")
    public void addMovie(@RequestBody Movie movie){
        //Genre genre = genreRepository.findByName(movie.getGenre().getName());
        movieRepository.save(movie);
        //movie.getGenre().getCurrentMoviesListed().add(movie);
        //genreRepository.save(genre);
    }

    @PutMapping("/update:{oldMovieId}")
    public void updateMovie(@PathVariable Integer oldMovieId, @RequestBody Movie updatedMovie){
        try{
            Movie oldMovie = movieRepository.findById(oldMovieId).get();
            updatedMovie.setId(oldMovie.getId());
            movieRepository.save(updatedMovie);
        } catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
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
