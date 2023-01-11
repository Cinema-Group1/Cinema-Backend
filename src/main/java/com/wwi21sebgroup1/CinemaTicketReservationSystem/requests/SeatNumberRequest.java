package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class SeatNumberRequest {
    private char line;
    private byte number;
    private int seatingPlanTemplateId;

    public SeatNumberRequest(){}

    public SeatNumberRequest(char line, byte number, int seatingPlanTemplateId) {
        this.line = line;
        this.number = number;
        this.seatingPlanTemplateId = seatingPlanTemplateId;
    }

    public char getLine() {return line;}

    public void setLine(char line) {this.line = line;}

    public byte getNumber() {return number;}

    public void setNumber(byte number) {this.number = number;}

    public int getSeatingPlanTemplateId() {
        return seatingPlanTemplateId;
    }

    public void setSeatingPlanTemplateId(int seatingPlanTemplateId) {
        this.seatingPlanTemplateId = seatingPlanTemplateId;
    }
}
