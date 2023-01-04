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

    public SeatNumber() {}

    public SeatNumber(char line, byte number) {
        this.line = line;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public char getLine() {
        return line;
    }

    public void setLine(char line) {
        this.line = line;
    }

    public byte getNumber() {
        return number;
    }

    public void setNumber(byte number) {
        this.number = number;
    }
}