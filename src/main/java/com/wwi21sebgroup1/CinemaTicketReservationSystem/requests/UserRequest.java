package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class UserRequest {
    private Integer id;
    private String name;

    public UserRequest(){}

    public UserRequest(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
