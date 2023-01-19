package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class SeatRequest {
    private int price;
    private boolean occupied;
    private int seatNumberId;
    private int seatingPlanId;

    public SeatRequest(){}

    public SeatRequest(int price, boolean occupied, int seatNumberId, int seatingPlanId) {
        this.price = price;
        this.occupied = occupied;
        this.seatNumberId = seatNumberId;
        this.seatingPlanId = seatingPlanId;
    }

    public int getSeatingPlanId() {
        return seatingPlanId;
    }

    public void setSeatingPlanId(int showingId) {
        this.seatingPlanId = seatingPlanId;
    }

    public int getSeatNumberId() {return seatNumberId;}

    public void setSeatNumberId(int seatNumberId) {this.seatNumberId = seatNumberId;}

    public int getPrice() {return price;}

    public void setPrice(int price) {this.price = price;}

    public boolean isOccupied() {return occupied;}

    public void setOccupied(boolean occupied) {this.occupied = occupied;}
}
