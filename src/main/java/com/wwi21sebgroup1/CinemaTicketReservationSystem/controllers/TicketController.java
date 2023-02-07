package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Ticket;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.TicketRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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