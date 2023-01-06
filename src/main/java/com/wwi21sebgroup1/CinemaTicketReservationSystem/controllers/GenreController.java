package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.GenreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    //Adding a Genre specified via the RequestBody.
    //Request Body takes in a JSON File and translates it to a Java Object.
    @PutMapping("/add")
    public void addGenre(@RequestBody GenreRequest genreRequest){
        genreRepository.save(transformRequestToObject(genreRequest));
    }

    @PutMapping("/update:{oldGenreName}")
    public void updateGenre(@PathVariable String oldGenreName, @RequestBody GenreRequest genreRequest){
        try {
            Genre updatedGenre = transformRequestToObject(genreRequest);
            updatedGenre.setName(oldGenreName);
            genreRepository.save(updatedGenre);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    @DeleteMapping("/delete:{oldGenreName}")
    public void deleteGenre(@PathVariable String oldGenreName) {
        try {
            genreRepository.delete(genreRepository.findByName(oldGenreName));
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Genre transformRequestToObject(GenreRequest genreRequest){
        return new Genre(genreRequest.getName(),genreRequest.getDescription());
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Genre> getGenres(){
        return genreRepository.findAll();
    }

}