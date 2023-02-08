package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class GenreRequest {
    private String name;
    private String description;

    public GenreRequest(){}

    public GenreRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {return name;}

    public String getDescription() {return description;}
}
