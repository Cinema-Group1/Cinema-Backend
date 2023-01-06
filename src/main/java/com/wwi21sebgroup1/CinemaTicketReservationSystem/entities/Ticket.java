package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Showing show;
    @OneToMany
    private List<Seat> seats;
    private double price;
    @ManyToOne
    private User user;

    public Ticket(){}

    public Ticket(Showing show, List<Seat> seats, double price, User user) {
        this.show = show;
        this.seats = seats;
        this.price = price;
        this.user = user;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public Showing getShow() {return show;}

    public void setShow(Showing show) {this.show = show;}

    public List<Seat> getSeats() {return seats;}

    public void setSeats(List<Seat> seats) {this.seats = seats;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}
}
