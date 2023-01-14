package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class CinemaHallRequest {
    private int cinemaId;
    private String name;

    public CinemaHallRequest(){}

    public CinemaHallRequest(Integer cinemaId, String name) {
        this.cinemaId = cinemaId;
        this.name = name;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}