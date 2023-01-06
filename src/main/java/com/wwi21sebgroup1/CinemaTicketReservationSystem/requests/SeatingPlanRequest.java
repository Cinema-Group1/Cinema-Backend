package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;

import java.util.HashSet;
import java.util.Set;

public class SeatingPlanRequest {
    private int id;
    private Set<Integer> seatIds = new HashSet<>();

    public SeatingPlanRequest(){}

    public SeatingPlanRequest(int id, Set<Integer> seatIds) {
        this.id = id;
        this.seatIds = seatIds;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public Set<Integer> getSeatIds() {return seatIds;}

    public void setSeatIds(Set<Integer> seatIds) {this.seatIds = seatIds;}
}
