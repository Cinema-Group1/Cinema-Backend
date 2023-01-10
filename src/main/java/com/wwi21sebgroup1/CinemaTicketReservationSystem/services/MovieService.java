package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.MovieRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;

    public void addMovie(MovieRequest movieRequest){
        movieRepository.save(transformRequestToObject(movieRequest));
    }

    public Iterable<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Iterable<Movie> getMoviesByGenre(String genreName){
        return movieRepository.findByGenreName(genreName);
    }

    public void updateMovie(Integer oldMovieId, MovieRequest movieRequest){
        try{
            Movie updatedMovie = transformRequestToObject(movieRequest);
            updatedMovie.setId(oldMovieId);
            movieRepository.save(updatedMovie);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void deleteMovie(Integer movieId){
        try {
            movieRepository.deleteById(movieId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Movie transformRequestToObject(MovieRequest movieRequest) {
        Genre genre = genreRepository.findByName(movieRequest.getGenreName()).get();
        return new Movie(   movieRequest.getTitle(),
                            movieRequest.getImagePath(),
                            movieRequest.getDescription(),
                            movieRequest.getLength(),
                            LocalDate.parse(movieRequest.getReleasedDateString()),
                            genre);
    }
}
