package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.GenreRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    GenreService genreService;

    @PutMapping("/add")
    public ResponseEntity<Object> addGenre(@RequestBody GenreRequest genreRequest){
        try{
            return new ResponseEntity<>(genreService.addGenre(genreRequest), HttpStatus.ACCEPTED);
        }catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<Object> getAllGenres(){
        return new ResponseEntity<>(genreService.getAllGenres(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update:{genreName}")
    public ResponseEntity<Object> updateGenre(@PathVariable String genreName, @RequestBody GenreRequest genreRequest){
        try{
            return new ResponseEntity<>(genreService.updateGenre(genreName, genreRequest), HttpStatus.ACCEPTED);
        }catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete:{genreName}")
    public ResponseEntity<Object> deleteGenre(@PathVariable String genreName) {
        try{
            genreService.deleteGenre(genreName);
            return new ResponseEntity<>("Successfully deleted genre " + genreName, HttpStatus.ACCEPTED);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }
}