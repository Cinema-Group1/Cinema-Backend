package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatNumber that = (SeatNumber) o;
        return line == that.line && number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, number);
    }
}