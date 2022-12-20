package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SeatNumber {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    private char line;
    private byte number;
}