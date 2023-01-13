package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Showing;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.User;

import java.util.List;

public class TicketRequest {
    private int showingId;
    private List<Integer> seatIds;
    private double price;
    private int userId;

    public TicketRequest(){}

    public TicketRequest(int showingId, List<Integer> seatIds, double price, int userId) {
        this.showingId = showingId;
        this.seatIds = seatIds;
        this.price = price;
        this.userId = userId;
    }

    public int getShowingId() {return showingId;}

    public void setShowingId(int showingId) {this.showingId = showingId;}

    public List<Integer> getSeatIds() {return seatIds;}

    public void setSeatIds(List<Integer> seatIds) {this.seatIds = seatIds;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}
}
