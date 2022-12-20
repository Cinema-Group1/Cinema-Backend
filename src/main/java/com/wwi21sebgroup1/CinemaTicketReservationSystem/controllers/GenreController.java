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

    @GetMapping("/add")
    public void addGenre(){
        genreRepository.save(new Genre("Horror", "spooky"));
    }
    @GetMapping("/all")
    public @ResponseBody Iterable<Genre> getGenres(){
        return genreRepository.findAll();
    }
}
