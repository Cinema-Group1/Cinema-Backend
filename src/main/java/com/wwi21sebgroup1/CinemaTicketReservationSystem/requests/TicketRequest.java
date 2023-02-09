package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class TicketRequest {
    private int bookingId;
    private int showingId;
    private int seatId;

    public TicketRequest(int bookingId, int showingId, int seatId) {
        this.bookingId = bookingId;
        this.showingId = showingId;
        this.seatId = seatId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getShowingId() {
        return showingId;
    }

    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
}
