package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    @PutMapping("/add")
    //Adding a Genre specified via the RequestBody.
    //Request Body takes in a JSON File and translates it to a Java Object.
    public void addGenre(@RequestBody Genre genre){
        genreRepository.save(genre);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Genre> getGenres(){
        return genreRepository.findAll();
    }

    @PutMapping("/update:{oldGenreName}")
    public void updateGenre(@PathVariable String oldGenreName, @RequestBody Genre updatedGenre){
        Genre oldGenre = genreRepository.findByName(oldGenreName);
        updatedGenre.setName(oldGenre.getName());
        genreRepository.save(updatedGenre);
    }

    @DeleteMapping("/delete:{genreName}")
    public void deleteGenre(@PathVariable String genreName){
        Genre toBeDeleted = genreRepository.findByName(genreName);
        genreRepository.delete(toBeDeleted);
    }
}
