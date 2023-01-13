package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;

@Entity
public class SeatingPlanTemplate {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    public SeatingPlanTemplate(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

