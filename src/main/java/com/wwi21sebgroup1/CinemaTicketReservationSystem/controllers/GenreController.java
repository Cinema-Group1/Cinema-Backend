package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.GenreRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    GenreService genreService;

    @PutMapping("/add")
    public void addGenre(@RequestBody GenreRequest genreRequest) throws InvalidRequestException {genreService.addGenre(genreRequest);}

    @PutMapping("/update:{oldGenreName}")
    public void updateGenre(@PathVariable String oldGenreName, @RequestBody GenreRequest genreRequest) throws InvalidRequestException {genreService.updateGenre(oldGenreName, genreRequest);}

    @DeleteMapping("/delete:{oldGenreName}")
    public void deleteGenre(@PathVariable String oldGenreName) {genreService.deleteGenre(oldGenreName);}

    @GetMapping("/all")
    public @ResponseBody Iterable<Genre> getGenres(){return genreService.getAllGenres();}
}