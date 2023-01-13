package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Showing;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Ticket;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.User;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.ShowingRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.TicketRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.UserRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.TicketRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/add")
    public void addTicket(@RequestBody TicketRequest ticketRequest) {
        ticketService.addTicket(ticketRequest);
    }

    @PostMapping("/update:{oldTicketId}")
    public void updateTicket(@PathVariable Integer oldTicketId, @RequestBody TicketRequest ticketRequest){
        ticketService.updateTicket(oldTicketId, ticketRequest);
    }

    @PostMapping("/delete:{oldTicketId}")
    public void deleteTicket(@PathVariable Integer oldTicketId) {
        ticketService.deleteTicket(oldTicketId);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Ticket> getTickets(){
        return ticketService.getTickets();
    }
}