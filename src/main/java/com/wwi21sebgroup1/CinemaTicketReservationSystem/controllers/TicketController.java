package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Ticket;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;
    @GetMapping("/all")
    public @ResponseBody Iterable<Ticket> getTickets(){
        return ticketRepository.findAll();
    }
}
