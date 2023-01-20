package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;

@Entity
public class SeatingPlanTemplate {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    CinemaHall cinemaHall;
    private int rows;
    private int seatsPerRow;
    public SeatingPlanTemplate(){}

    public SeatingPlanTemplate(CinemaHall cinemaHall, int rows, int seatsPerRow) {
        this.cinemaHall = cinemaHall;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }
}

