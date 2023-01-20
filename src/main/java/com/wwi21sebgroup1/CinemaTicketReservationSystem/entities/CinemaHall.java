package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.util.Objects;

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

    public CinemaHall(){}

    public CinemaHall(Cinema cinema, String name) {
        this.cinema = cinema;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaHall that = (CinemaHall) o;
        return id.equals(that.id) && cinema.equals(that.cinema) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cinema, name);
    }
}
