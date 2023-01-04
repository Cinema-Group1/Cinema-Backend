package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.MovieRequest;
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

    @PostMapping("/add")
    public void addMovie(@RequestBody MovieRequest movieRequest){
        Genre genre = genreRepository.findByName(movieRequest.getGenreName());
        movieRepository.save(new Movie( movieRequest.getTitle(),
                                        movieRequest.getLength(),
                                        movieRequest.getReleasedDate(),
                                        genre));
    }

    @PostMapping("/update:{oldMovieId}")
    public void updateMovie(@PathVariable Integer oldMovieId, @RequestBody MovieRequest movieRequest){
        try{
            Movie oldMovie = movieRepository.findById(oldMovieId).get();
            Genre genre = genreRepository.findByName(movieRequest.getGenreName());
            Movie updatedMovie = new Movie( movieRequest.getTitle(),
                                            movieRequest.getLength(),
                                            movieRequest.getReleasedDate(),
                                            genre);
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
