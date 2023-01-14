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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShowingId() {
        return showingId;
    }

    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }

    public List<String> getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(List<String> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }
}