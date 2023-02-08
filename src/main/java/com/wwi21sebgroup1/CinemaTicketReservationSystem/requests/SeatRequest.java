package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class SeatRequest {
    private final int price;
    private final boolean occupied;
    private final int seatNumberId;
    private final int seatingPlanId;

    public SeatRequest(int price, boolean occupied, int seatNumberId, int seatingPlanId) {
        this.price = price;
        this.occupied = occupied;
        this.seatNumberId = seatNumberId;
        this.seatingPlanId = seatingPlanId;
    }

    public int getSeatingPlanId() {
        return seatingPlanId;
    }

    public int getSeatNumberId() {return seatNumberId;}

    public int getPrice() {return price;}

    public boolean isOccupied() {return occupied;}
}
