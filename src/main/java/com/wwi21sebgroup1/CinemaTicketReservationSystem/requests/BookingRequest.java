package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import java.util.List;

public class BookingRequest {
    private int userId;
    private int showingId;
    private List<String> seatNumbers;
    private int price;

    public BookingRequest(){}

    public BookingRequest(int userId, int showingId, List<String> seatNumbers, int price) {
        this.userId = userId;
        this.showingId = showingId;
        this.seatNumbers = seatNumbers;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}