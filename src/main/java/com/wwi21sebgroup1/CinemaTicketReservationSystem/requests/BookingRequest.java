package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import java.util.List;

public class BookingRequest {
    private final int userId;
    private final int showingId;
    private final List<String> seatNumbers;

    public BookingRequest(int userId, int showingId, List<String> seatNumbers) {
        this.userId = userId;
        this.showingId = showingId;
        this.seatNumbers = seatNumbers;
    }

    public int getUserId() {
        return userId;
    }

    public int getShowingId() {
        return showingId;
    }

    public List<String> getSeatNumbers() {return seatNumbers;}
}