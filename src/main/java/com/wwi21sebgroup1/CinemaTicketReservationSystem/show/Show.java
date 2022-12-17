package com.wwi21sebgroup1.CinemaTicketReservationSystem.show;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.movie.Movie;

import javax.persistence.*;

@Entity
public class Show {

    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    private String title;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private java.sql.Time startTime;
    private java.sql.Time endTime;
    @ManyToOne
    private Movie movie;

    public Show(){}

    public Show(String title,
                java.sql.Date startDate,
                java.sql.Date endDate,
                java.sql.Time startTime,
                java.sql.Time endTime,
                Movie movie){
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
    }
}
