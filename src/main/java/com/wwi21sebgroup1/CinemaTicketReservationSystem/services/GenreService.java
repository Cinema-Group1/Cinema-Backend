package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.GenreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public Genre addGenre(GenreRequest genreRequest) throws InvalidRequestException {
        Genre genre = processRequest(genreRequest);
        genreRepository.save(genre);
        return genre;
    }

    public Iterable<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    public Genre updateGenre(String genreName, GenreRequest genreRequest) throws NoSuchElementException, InvalidRequestException {
        if(genreRepository.findByName(genreName).isEmpty()){
            throw new NoSuchElementException();
        }
        Genre updatedGenre = processRequest(genreRequest);
        updatedGenre.setName(genreName);
        genreRepository.save(updatedGenre);
        return updatedGenre;
    }

    public void deleteGenre(String genreName) throws NoSuchElementException{
        genreRepository.delete(genreRepository.findByName(genreName).get());
    }

    public Genre processRequest(GenreRequest genreRequest) throws InvalidRequestException {
        if(genreRequest.getName() == null || genreRequest.getDescription() == null){
            throw new InvalidRequestException("genreRequest");
        }
        if(genreRequest.getName().isBlank() || genreRequest.getDescription().isBlank()){
            throw new InvalidRequestException("genreRequest");
        }
        return new Genre(genreRequest.getName(),genreRequest.getDescription());
    }
}
