package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Showing {

    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    private String title;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private java.sql.Time startTime;
    private java.sql.Time endTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cinema_hall_id", referencedColumnName = "id")
    private CinemaHall cinemaHall;
    @OneToOne
    private SeatingPlan seatingPlan;

    public Showing(){}
    public Showing(String title, Date startDate, Date endDate, Time startTime, Time endTime, Movie movie, CinemaHall cinemaHall, SeatingPlan seatingPlan) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
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
