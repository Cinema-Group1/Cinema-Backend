package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;

public class SeatRequest {
    private int price;
    private boolean occupied;
    private int seatNumberId;
    private int showingId;

    public SeatRequest(){}

    public SeatRequest(int price, boolean occupied, int seatNumberId, int showingId) {
        this.price = price;
        this.occupied = occupied;
        this.seatNumberId = seatNumberId;
        this.showingId = showingId;
    }

    public int getShowingId() {
        return showingId;
    }

    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }

    public int getSeatNumberId() {return seatNumberId;}

    public void setSeatNumberId(int seatNumberId) {this.seatNumberId = seatNumberId;}

    public int getPrice() {return price;}

    public void setPrice(int price) {this.price = price;}

    public boolean isOccupied() {return occupied;}

    public void setOccupied(boolean occupied) {this.occupied = occupied;}
}
