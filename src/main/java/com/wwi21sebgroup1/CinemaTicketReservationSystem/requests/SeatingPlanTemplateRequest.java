package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class SeatingPlanTemplateRequest {
    private final int rows;
    private final int seatsPerRow;
    private final int cinemaHallId;

    public SeatingPlanTemplateRequest(int rows, int seatsPerRow, int cinemaHallId) {
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.cinemaHallId = cinemaHallId;
    }

    public int getRows() {
        return rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public int getCinemaHallId() {
        return cinemaHallId;
    }
}