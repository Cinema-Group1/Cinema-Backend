package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Entity
public class Showing {

    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    private String title;
    private SimpleDateFormat startDate;
    private SimpleDateFormat endDate;
    private SimpleDateFormat startTime;
    private SimpleDateFormat endTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cinema_hall_id", referencedColumnName = "id")
    private CinemaHall cinemaHall;
    @OneToOne
    private SeatingPlan seatingPlan;

    public Showing(){}
    public Showing(String title, SimpleDateFormat startDate, SimpleDateFormat endDate, SimpleDateFormat startTime, SimpleDateFormat endTime, Movie movie, CinemaHall cinemaHall, SeatingPlan seatingPlan) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.cinemaHall = cinemaHall;
        this.seatingPlan = seatingPlan;
    }

    public SeatingPlan getSeatingPlan() {
        return seatingPlan;
    }

    public void setSeatingPlan(SeatingPlan seatingPlan) {
        this.seatingPlan = seatingPlan;
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

    public SimpleDateFormat getStartDate() {
        return startDate;
    }

    public void setStartDate(SimpleDateFormat startDate) {
        this.startDate = startDate;
    }

    public SimpleDateFormat getEndDate() {
        return endDate;
    }

    public void setEndDate(SimpleDateFormat endDate) {
        this.endDate = endDate;
    }

    public SimpleDateFormat getStartTime() {
        return startTime;
    }

    public void setStartTime(SimpleDateFormat startTime) {
        this.startTime = startTime;
    }

    public SimpleDateFormat getEndTime() {
        return endTime;
    }

    public void setEndTime(SimpleDateFormat endTime) {
        this.endTime = endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }
}
