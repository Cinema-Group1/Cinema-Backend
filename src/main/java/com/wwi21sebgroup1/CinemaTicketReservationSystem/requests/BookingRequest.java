package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import java.util.List;

public class BookingRequest {
    private int userId;
    private int showingId;
    private List<String> seatNumbers;

    public BookingRequest(){}

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