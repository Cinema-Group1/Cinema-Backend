package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class SeatingPlan {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private SeatingPlanTemplate seatingPlanTemplate;
    @OneToMany
    private List<Seat> seats;

    public SeatingPlan(){}

    public SeatingPlan(SeatingPlanTemplate seatingPlanTemplate) {
        this.seatingPlanTemplate = seatingPlanTemplate;
        //TODO: create SeatingPlan based on template
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SeatingPlanTemplate getSeatingPlanTemplate() {
        return seatingPlanTemplate;
    }

    public void setSeatingPlanTemplate(SeatingPlanTemplate seatingPlanTemplate) {
        this.seatingPlanTemplate = seatingPlanTemplate;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
