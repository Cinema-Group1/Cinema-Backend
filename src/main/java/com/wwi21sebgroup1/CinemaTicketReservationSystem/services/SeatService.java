package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlan;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Showing;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.ShowingRepository;
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
    @Autowired
    private SeatingPlanRepository seatingPlanRepository;
    @Autowired
    private ShowingRepository showingRepository;

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

    public Iterable<Seat> getSeatsBySeatingPlan(Integer seatingPlanId){
        return seatRepository.findAllBySeatingPlanId(seatingPlanId);
    }

    public Seat transformRequestToObject(SeatRequest seatRequest){
        SeatNumber seatNumber = seatNumberRepository.findById(seatRequest.getSeatNumberId()).get();
        Showing showing = showingRepository.findById(seatRequest.getShowingId()).get();
        SeatingPlan seatingPlan = seatingPlanRepository.findById(showing.getSeatingPlan().getId()).get();
        return new Seat(seatRequest.getPrice(), seatRequest.isOccupied(), seatingPlan, seatNumber);
    }
}
