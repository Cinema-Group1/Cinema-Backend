package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class SeatingPlanTemplateRequest {
    private int rows;
    private int seatsPerRow;
    public SeatingPlanTemplateRequest() {}
    public SeatingPlanTemplateRequest(int rows, int seatsPerRow) {
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }
}
