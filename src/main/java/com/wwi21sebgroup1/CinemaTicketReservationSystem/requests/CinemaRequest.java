package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;

public class CinemaRequest {

    private int id;
    private int addressId;

    public CinemaRequest(){}

    public CinemaRequest(Integer id, int addressId) {
        this.id = id;
        this.addressId = addressId;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public int getAddressId() {return addressId;}

    public void setAddressId(int addressId) {this.addressId = addressId;}
}
