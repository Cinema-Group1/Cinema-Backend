package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import java.util.List;

public class BookingRequest {
    private int userId;
    private int showingId;
    private List<Integer> seatIds;
    private int price;

    public BookingRequest(){}

    public BookingRequest(int userId, int showingId, List<Integer> seatIds) {
        this.userId = userId;
        this.showingId = showingId;
        this.seatIds = seatIds;
    }

    public int getUserId() {return userId;}

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getShowingId() {
        return showingId;
    }

    public void setShowingId(Integer showingId) {
        this.showingId = showingId;
    }

    public List<Integer> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Integer> seatIds) {
        this.seatIds = seatIds;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}