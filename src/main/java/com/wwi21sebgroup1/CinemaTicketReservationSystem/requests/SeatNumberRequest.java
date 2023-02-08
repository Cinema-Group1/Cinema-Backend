package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class SeatNumberRequest {
    private final char line;
    private final byte number;

    public SeatNumberRequest(char line, byte number) {
        this.line = line;
        this.number = number;
    }

    public char getLine() {return line;}

    public byte getNumber() {return number;}
}
