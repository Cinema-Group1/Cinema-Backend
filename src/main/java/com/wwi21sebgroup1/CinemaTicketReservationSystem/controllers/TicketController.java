package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Ticket;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidTicketException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.TicketRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/bookingId:{bookingId}")
    public @ResponseBody Iterable<Ticket> getTicketsByBookingId(@PathVariable int bookingId){
        return ticketService.getTicketsByBookingId(bookingId);
    }
    @PostMapping("check:{ticketId}")
    public @ResponseBody ResponseEntity<Object> checkTicket(@PathVariable int ticketId){
        try{
            return new ResponseEntity<>(ticketService.checkTicket(ticketId), HttpStatus.ACCEPTED);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.FORBIDDEN);
        }
    }
}