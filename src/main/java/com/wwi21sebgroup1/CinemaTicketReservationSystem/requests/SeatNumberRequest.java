package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class SeatNumberRequest {
    private char line;
    private byte number;

    public SeatNumberRequest(){}

    public SeatNumberRequest(char line, byte number) {
        this.line = line;
        this.number = number;
    }

    public char getLine() {return line;}

    public void setLine(char line) {this.line = line;}

    public byte getNumber() {return number;}

    public void setNumber(byte number) {this.number = number;}
}
