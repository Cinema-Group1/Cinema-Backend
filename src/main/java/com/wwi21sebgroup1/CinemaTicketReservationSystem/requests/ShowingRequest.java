package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.CinemaHall;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

public class ShowingRequest {
    private int id;
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

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Integer cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(String startsAt) {
        this.startsAt = startsAt;
    }

    public String getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(String endsAt) {
        this.endsAt = endsAt;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setCinemaHallId(int cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public int getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(int pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }
}