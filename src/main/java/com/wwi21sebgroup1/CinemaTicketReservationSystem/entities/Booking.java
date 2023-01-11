package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Showing showing;
    @OneToMany
    private List<Seat> Seats;
    private int price;

    public Booking() {}

    public Booking(User user, Showing showing, List<Seat> Seats, int price) {
        this.user = user;
        this.showing = showing;
        this.Seats = Seats;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public List<Seat> getSeats() {
        return Seats;
    }

    public void setSeats(List<Seat> Seats) {
        this.Seats = Seats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && price == booking.price && user.equals(booking.user) && showing.equals(booking.showing) && Seats.equals(booking.Seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, showing, Seats, price);
    }
}
