package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidTicketException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.ShowingRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.TicketRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.UserRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.TicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean checkTicket(Integer ticketId)throws InvalidTicketException {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        if(ticket.isValid()){
            ticket.setValid(false);
            return true;
        }else {
            throw new InvalidTicketException();
        }
    }

    public Iterable<Ticket> getTicketsByBookingId(int bookingId){
        return ticketRepository.findByBookingId(bookingId);
    }

    public Ticket transformRequestToObject(TicketRequest ticketRequest){
        Showing showing = showingRepository.findById(ticketRequest.getShowingId()).get();
        Seat seat = seatRepository.findById(ticketRequest.getSeatId()).get();
        return new Ticket(showing, seat.getSeatNumber(), new Booking());
    }
}
