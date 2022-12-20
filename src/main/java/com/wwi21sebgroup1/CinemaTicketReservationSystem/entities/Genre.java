package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String description;
    @OneToMany
    private List<Movie> currentMoviesListed;

    public Genre(){}

    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Movie> getCurrentMoviesListed() {
        return currentMoviesListed;
    }

    public void setCurrentMoviesListed(List<Movie> currentMoviesListed) {
        this.currentMoviesListed = currentMoviesListed;
    }
}
