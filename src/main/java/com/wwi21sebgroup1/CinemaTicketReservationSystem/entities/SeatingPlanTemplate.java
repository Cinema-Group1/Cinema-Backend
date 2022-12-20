package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class SeatingPlanTemplate {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private Set<SeatNumber> seats;

    public SeatingPlanTemplate(){}

    public SeatingPlanTemplate(Set<SeatNumber> seats) {
        this.seats = seats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<SeatNumber> getSeats() {
        return seats;
    }

    public void setSeats(Set<SeatNumber> seats) {
        this.seats = seats;
    }
}
