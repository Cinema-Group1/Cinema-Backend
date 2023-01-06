package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Cinema;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.AddressRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.CinemaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private AddressRepository addressRepository;
    @PutMapping("/add")

    public void addCinema(@RequestBody CinemaRequest cinemaRequest){
        cinemaRepository.save(transformRequestToObject(cinemaRequest));
    }

    @PostMapping("/update:{oldCinemaId}")
    public void updateCinema(@PathVariable Integer oldCinemaId, @RequestBody CinemaRequest cinemaRequest) {
        try {
            Cinema updatedCinema = transformRequestToObject(cinemaRequest);
            updatedCinema.setId(oldCinemaId);
            cinemaRepository.save(updatedCinema);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    @PostMapping("/delete:{oldCinemaId}")
    public void deleteCinema(@PathVariable Integer oldCinemaId) {
        try {
            cinemaRepository.deleteById(oldCinemaId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Cinema transformRequestToObject(CinemaRequest cinemaRequest){
        Address address = addressRepository.findById(cinemaRequest.getId()).get();
        return new Cinema(address);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Cinema> getCinemas(){
        return cinemaRepository.findAll();
    }
}