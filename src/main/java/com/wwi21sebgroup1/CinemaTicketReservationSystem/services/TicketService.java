package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowingRepository showingRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    UserRepository userRepository;

    public void addTicket(TicketRequest ticketRequest) {
        ticketRepository.save(transformRequestToObject(ticketRequest));
    }

    public void updateTicket(Integer oldTicketId, TicketRequest ticketRequest){
        try{
            Ticket updatedTicket = transformRequestToObject(ticketRequest);
            updatedTicket.setId(oldTicketId);
            ticketRepository.save(updatedTicket);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void deleteTicket(Integer oldTicketId) {
        try {
            ticketRepository.deleteById(oldTicketId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Iterable<Ticket> getTickets(){
        return ticketRepository.findAll();
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
}
