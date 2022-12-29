package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Showing;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.ShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/showing")
public class ShowingController {
    @Autowired
    private ShowingRepository showingRepository;

    @PutMapping("/add")
    //Adding a Genre specified via the RequestBody.
    //Request Body takes in a JSON File and translates it to a Java Object.
    public void addShowing(@RequestBody Showing showing){
        showingRepository.save(showing);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Showing> getShowings() {
        return showingRepository.findAll();
    }
}
