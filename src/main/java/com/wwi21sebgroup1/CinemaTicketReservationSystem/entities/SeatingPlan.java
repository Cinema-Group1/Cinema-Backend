package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class SeatingPlan {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private List<Seat> seats = new ArrayList<>();

    public SeatingPlan(){}

    public SeatingPlan(List<Seat> seats){
        this.seats =seats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void addSeat(Seat seat){
        this.seats.add(seat);
    }
}
