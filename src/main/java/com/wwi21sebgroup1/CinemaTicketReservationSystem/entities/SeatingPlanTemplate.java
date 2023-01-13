package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatingPlanTemplate that = (SeatingPlanTemplate) o;
        return id.equals(that.id) && Objects.equals(seatNumbers, that.seatNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seatNumbers);
    }
}

