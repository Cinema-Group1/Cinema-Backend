package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Showing {

    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    private String title;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cinema_hall_id", referencedColumnName = "id")
    private CinemaHall cinemaHall;

    private int pricePerSeat;

    public Showing(){}

    public Showing(String title, LocalDateTime startsAt, LocalDateTime endsAt, Movie movie, CinemaHall cinemaHall, int pricePerSeat) {
        this.title = title;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.movie = movie;
        this.cinemaHall = cinemaHall;
        this.pricePerSeat = pricePerSeat;
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

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(LocalDateTime startsAt) {
        this.startsAt = startsAt;
    }

    public LocalDateTime getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(LocalDateTime endsAt) {
        this.endsAt = endsAt;
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

    public int getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(int pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Showing showing = (Showing) o;
        return Objects.equals(title, showing.title) && Objects.equals(startsAt, showing.startsAt) && Objects.equals(endsAt, showing.endsAt) && Objects.equals(movie, showing.movie) && Objects.equals(cinemaHall, showing.cinemaHall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startsAt, endsAt, movie, cinemaHall);
    }
}
