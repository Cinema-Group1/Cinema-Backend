package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class CinemaHallRequest {
    private final int cinemaId;
    private final String name;

    public CinemaHallRequest(Integer cinemaId, String name) {
        this.cinemaId = cinemaId;
        this.name = name;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public String getName() {
        return name;
    }
}