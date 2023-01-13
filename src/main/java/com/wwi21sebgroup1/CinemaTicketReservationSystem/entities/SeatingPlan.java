package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SeatingPlan {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    public SeatingPlan(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
