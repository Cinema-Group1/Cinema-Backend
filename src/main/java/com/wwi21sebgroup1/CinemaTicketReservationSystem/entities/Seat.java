package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    private int price;
    private boolean occupied;
    @ManyToOne
    private SeatingPlan seatingPlan;
    @ManyToOne
    private SeatNumber seatNumber;

    public Seat(){}
    public Seat(int price, boolean occupied, SeatingPlan seatingPlan, SeatNumber seatNumber) {
        this.price = price;
        this.occupied = occupied;
        this.seatingPlan = seatingPlan;
        this.seatNumber = seatNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public SeatingPlan getSeatingPlan() {
        return seatingPlan;
    }

    public void setSeatingPlan(SeatingPlan seatingPlan) {
        this.seatingPlan = seatingPlan;
    }

    public SeatNumber getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(SeatNumber seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return price == seat.price && occupied == seat.occupied && seatingPlan.equals(seat.seatingPlan) && seatNumber.equals(seat.seatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, occupied, seatingPlan, seatNumber);
    }
}
