package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class ShowingRequest {
    private String title;
    //given String must be in format: yyyy-mm-ddThh:mm:ss
    //i.e. 2015-08-04T10:11:30
    private String startsAt;
    private String endsAt;
    private int movieId;
    private int cinemaHallId;
    private int pricePerSeat;

    public ShowingRequest(){}

    public ShowingRequest(String title, String startsAt, String endsAt, int movieId, int cinemaHallId, int pricePerSeat) {
        this.title = title;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.movieId = movieId;
        this.cinemaHallId = cinemaHallId;
        this.pricePerSeat = pricePerSeat;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public Integer getCinemaHallId() {
        return cinemaHallId;
    }

    public String getTitle() {
        return title;
    }

    public String getStartsAt() {
        return startsAt;
    }

    public String getEndsAt() {
        return endsAt;
    }

    public int getPricePerSeat() {
        return pricePerSeat;
    }
}