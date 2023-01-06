package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class SeatNumberRequest {
    private int id;
    private char line;
    private byte number;

    public SeatNumberRequest(){}

    public SeatNumberRequest(int id, char line, byte number) {
        this.id = id;
        this.line = line;
        this.number = number;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public char getLine() {return line;}

    public void setLine(char line) {this.line = line;}

    public byte getNumber() {return number;}

    public void setNumber(byte number) {this.number = number;}
}
