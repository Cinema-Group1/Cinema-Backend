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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShowingRepository showingRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public void addTicket(@RequestBody TicketRequest ticketRequest) {
        ticketRepository.save(transformRequestToObject(ticketRequest));
    }

    @PostMapping("/update:{oldTicketId}")
    public void updateTicket(@PathVariable Integer oldTicketId, @RequestBody TicketRequest ticketRequest){
        try{
            Ticket updatedTicket = transformRequestToObject(ticketRequest);
            updatedTicket.setId(oldTicketId);
            ticketRepository.save(updatedTicket);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    @PostMapping("/delete:{oldTicketId}")
    public void deleteTicket(@PathVariable Integer oldTicketId) {
        try {
            ticketRepository.deleteById(oldTicketId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Ticket transformRequestToObject(TicketRequest ticketRequest){
        Showing showing = showingRepository.findById(ticketRequest.getShowingId()).get();
        List<Seat> seats = new ArrayList<>();
        for(int seatId: ticketRequest.getSeatIds()){
            seats.add(seatRepository.findById(seatId).get());
        }
        User user = userRepository.findById(ticketRequest.getUserId()).get();
        return new Ticket(showing, seats, ticketRequest.getPrice(), user);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Ticket> getTickets(){
        return ticketRepository.findAll();
    }
}