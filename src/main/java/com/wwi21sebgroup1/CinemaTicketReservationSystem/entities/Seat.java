package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private SeatNumber seatNumber;
    private int price;
    private boolean occupied;

    public Seat(){}

    public Seat(SeatNumber seatNumber, int price, boolean occupied) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.occupied = occupied;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SeatNumber getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(SeatNumber seatNumber) {
        this.seatNumber = seatNumber;
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
}
