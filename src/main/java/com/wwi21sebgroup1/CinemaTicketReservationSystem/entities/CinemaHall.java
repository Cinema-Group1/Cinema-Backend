package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;

@Entity
public class CinemaHall {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
    private Cinema cinema;
    private String name;
    @OneToOne
    private SeatingPlanTemplate seatingPlanTemplate;

    public CinemaHall(){}

    public CinemaHall(Cinema cinema, String name, SeatingPlanTemplate seatingPlanTemplate) {
        this.cinema = cinema;
        this.name = name;
        this.seatingPlanTemplate = seatingPlanTemplate;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SeatingPlanTemplate getSeatingPlanTemplate() {
        return seatingPlanTemplate;
    }

    public void setSeatingPlanTemplate(SeatingPlanTemplate seatingPlanTemplate) {
        this.seatingPlanTemplate = seatingPlanTemplate;
    }
}
