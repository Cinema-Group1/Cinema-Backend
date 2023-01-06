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
    private Set<SeatNumber> seatNumbers;
    public SeatingPlanTemplate(){}
    public SeatingPlanTemplate(Set<SeatNumber> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<SeatNumber> getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(Set<SeatNumber> seatNumbers) {this.seatNumbers = seatNumbers;}
}

