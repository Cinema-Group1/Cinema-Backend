package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class CinemaHallRequest {
    private int cinemaId;
    private String name;
    private int seatingPlanTemplateId;

    public CinemaHallRequest(){}

    public CinemaHallRequest(Integer cinemaId, String name, Integer seatingPlanTemplateId) {
        this.cinemaId = cinemaId;
        this.name = name;
        this.seatingPlanTemplateId = seatingPlanTemplateId;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeatingPlanTemplateId() {
        return seatingPlanTemplateId;
    }

    public void setSeatingPlanTemplateId(Integer seatingPlanTemplateId) {
        this.seatingPlanTemplateId = seatingPlanTemplateId;
    }
}