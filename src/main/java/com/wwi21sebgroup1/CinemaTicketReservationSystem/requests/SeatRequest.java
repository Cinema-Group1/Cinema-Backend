package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;

public class SeatRequest {
    private int id;
    private int seatNumberId;
    private int price;
    private boolean occupied;

    public SeatRequest(){}

    public SeatRequest(Integer id, int seatNumberId, int price, boolean occupied) {
        this.id = id;
        this.seatNumberId = seatNumberId;
        this.price = price;
        this.occupied = occupied;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public int getSeatNumberId() {return seatNumberId;}

    public void setSeatNumberId(int seatNumberId) {this.seatNumberId = seatNumberId;}

    public int getPrice() {return price;}

    public void setPrice(int price) {this.price = price;}

    public boolean isOccupied() {return occupied;}

    public void setOccupied(boolean occupied) {this.occupied = occupied;}
}
