package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Show;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/show")
public class ShowController {
    @Autowired
    private ShowRepository showRepository;

    @GetMapping
    public @ResponseBody Iterable<Show> getShows() {
        return showRepository.findAll();
    }
}
