package com.wwi21sebgroup1.CinemaTicketReservationSystem.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/show")
public class ShowController {
    @Autowired
    private showRepository showRepository;

    @GetMapping
    public @ResponseBody Iterable<Show> getUsers() {
        return showRepository.findAll();
    }
}
