package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;

import java.util.HashSet;
import java.util.Set;

public class SeatingPlanRequest {
    private Set<Integer> seatIds = new HashSet<>();

    public SeatingPlanRequest(){}

    public SeatingPlanRequest(Set<Integer> seatIds) {
        this.seatIds = seatIds;
    }

    public Set<Integer> getSeatIds() {return seatIds;}

    public void setSeatIds(Set<Integer> seatIds) {this.seatIds = seatIds;}
}
