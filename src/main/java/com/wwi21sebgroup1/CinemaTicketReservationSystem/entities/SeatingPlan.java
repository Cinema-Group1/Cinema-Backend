package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class SeatingPlan {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Showing showing;

    public SeatingPlan(){}

    public SeatingPlan(Showing showing) {
        this.showing = showing;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatingPlan that = (SeatingPlan) o;
        return Objects.equals(showing, that.showing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showing);
    }
}
