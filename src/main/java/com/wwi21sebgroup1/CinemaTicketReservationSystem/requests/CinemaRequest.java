package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class CinemaRequest {
    private int addressId;

    public CinemaRequest(){}

    public CinemaRequest(int addressId) {
        this.addressId = addressId;
    }

    public int getAddressId() {return addressId;}

    public void setAddressId(int addressId) {this.addressId = addressId;}
}
