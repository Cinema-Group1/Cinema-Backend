package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;

public class SeatRequest {
    private int seatNumberId;
    private int price;
    private boolean occupied;

    public SeatRequest(){}

    public SeatRequest(int seatNumberId, int price, boolean occupied) {
        this.seatNumberId = seatNumberId;
        this.price = price;
        this.occupied = occupied;
    }

    public int getSeatNumberId() {return seatNumberId;}

    public void setSeatNumberId(int seatNumberId) {this.seatNumberId = seatNumberId;}

    public int getPrice() {return price;}

    public void setPrice(int price) {this.price = price;}

    public boolean isOccupied() {return occupied;}

    public void setOccupied(boolean occupied) {this.occupied = occupied;}
}
