package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class SeatingPlanTemplateRequest {
    private int rows;
    private int seatsPerRow;
    private int cinemaHallId;

    public SeatingPlanTemplateRequest() {}

    public SeatingPlanTemplateRequest(int rows, int seatsPerRow, int cinemaHallId) {
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.cinemaHallId = cinemaHallId;
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

    public int getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(int cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }
}