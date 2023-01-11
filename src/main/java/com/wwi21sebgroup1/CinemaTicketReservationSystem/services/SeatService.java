package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SeatService {
    @Autowired
    private SeatNumberRepository seatNumberRepository;
    @Autowired
    private SeatRepository seatRepository;

    public void addSeat(SeatRequest seatRequest){seatRepository.save(transformRequestToObject(seatRequest));}

    public void updateSeat(Integer oldSeatId, SeatRequest seatRequest){
        try {
            Seat updatedSeat = transformRequestToObject(seatRequest);
            updatedSeat.setId(oldSeatId);
            seatRepository.save(updatedSeat);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void deleteSeat(Integer oldSeatId) {
        try {
            seatRepository.deleteById(oldSeatId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Iterable<Seat> getSeats(){return seatRepository.findAll();}

    public Seat transformRequestToObject(SeatRequest seatRequest){
        SeatNumber seatNumber = seatNumberRepository.findById(seatRequest.getSeatNumberId()).get();
        return new Seat(seatNumber,seatRequest.getPrice(),seatRequest.isOccupied());
    }
}
