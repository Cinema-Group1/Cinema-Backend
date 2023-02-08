package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.NoSuchGenreException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.MovieRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;

    public Movie addMovie(MovieRequest movieRequest) throws InvalidRequestException, NoSuchElementException{
        return movieRepository.save(processRequest(movieRequest));
    }

    public Iterable<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Iterable<Movie> getMoviesByGenre(String genreName) throws NoSuchGenreException{
        if(genreRepository.findByName(genreName).isEmpty()){
            throw new NoSuchGenreException(genreName);
        }
        return movieRepository.findByGenreName(genreName);
    }

    public Movie updateMovie(Integer id, MovieRequest movieRequest) throws InvalidRequestException, NoSuchElementException {
        movieRepository.findById(id);
        Movie updatedMovie = processRequest(movieRequest);
        updatedMovie.setId(id);
        movieRepository.save(updatedMovie);
        return updatedMovie;
    }

    public void deleteMovie(Integer movieId) throws NoSuchElementException{
        movieRepository.deleteById(movieId);
    }

    public Movie processRequest(MovieRequest movieRequest) throws InvalidRequestException, NoSuchElementException{
        if( movieRequest.getTitle().isBlank() || movieRequest.getReleaseDate().isBlank() ||
            movieRequest.getGenreName().isBlank()){
            throw new InvalidRequestException("MovieRequest");
        }
        Genre genre = genreRepository.findByName(movieRequest.getGenreName()).get();
        return new Movie(   movieRequest.getTitle(),
                            movieRequest.getImagePath(),
                            movieRequest.getDescription(),
                            movieRequest.getLength(),
                            LocalDate.parse(movieRequest.getReleaseDate()),
                            genre);
    }
}
