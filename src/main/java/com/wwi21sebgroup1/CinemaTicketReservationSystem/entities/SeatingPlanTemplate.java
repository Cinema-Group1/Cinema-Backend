package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class SeatingPlanTemplate {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    @OneToMany
    private List<SeatNumber> seatNumbers;
    public SeatingPlanTemplate(){}
    public SeatingPlanTemplate(List<SeatNumber> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<SeatNumber> getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(List<SeatNumber> seatNumbers) {this.seatNumbers = seatNumbers;}
}

