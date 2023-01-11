package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.GenreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public void addGenre(GenreRequest genreRequest){genreRepository.save(transformRequestToObject(genreRequest));}

    public void updateGenre(String oldGenreName, GenreRequest genreRequest){
        try {
            Genre updatedGenre = transformRequestToObject(genreRequest);
            updatedGenre.setName(oldGenreName);
            genreRepository.save(updatedGenre);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void deleteGenre(String oldGenreName) {
        try {
            genreRepository.delete(genreRepository.findByName(oldGenreName).get());
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Genre transformRequestToObject(GenreRequest genreRequest){
        return new Genre(genreRequest.getName(),genreRequest.getDescription());
    }

    public Iterable<Genre> getGenres(){return genreRepository.findAll();}
}
