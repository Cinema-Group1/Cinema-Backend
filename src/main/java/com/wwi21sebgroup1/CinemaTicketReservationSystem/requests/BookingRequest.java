package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.User;

import javax.persistence.*;
import java.util.List;

public class BookingRequest {
    private Integer userId;
    private Integer showingId;
    private List<Integer> seats;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShowingId() {
        return showingId;
    }

    public void setShowingId(Integer showingId) {
        this.showingId = showingId;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }
}
