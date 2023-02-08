package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class CinemaRequest {
    private final int addressId;

    public CinemaRequest(int addressId) {
        this.addressId = addressId;
    }

    public int getAddressId() {return addressId;}
}
