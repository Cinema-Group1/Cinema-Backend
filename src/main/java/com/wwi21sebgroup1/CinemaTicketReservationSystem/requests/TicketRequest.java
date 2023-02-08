package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class TicketRequest {
    private final int showingId;
    private final int seatId;

    public TicketRequest(int showingId, int seatId) {
        this.showingId = showingId;
        this.seatId = seatId;
    }

    public int getShowingId() {
        return showingId;
    }

    public int getSeatId() {
        return seatId;
    }
}
