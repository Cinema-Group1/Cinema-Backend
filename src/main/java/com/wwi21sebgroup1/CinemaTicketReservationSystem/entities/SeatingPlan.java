package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SeatingPlan {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private Set<Seat> seats = new HashSet<Seat>();

    public SeatingPlan(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public void addSeat(Seat seat){
        this.seats.add(seat);
    }
}
