package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class AddressRequest {
    private final String zipCode;
    private final String city;
    private final String street;
    private final String number;
    private final String additionalInformation;

    public AddressRequest(String zipCode, String city, String street, String number) {
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.additionalInformation = "";
    }

    public AddressRequest(String zipCode, String city, String street, String number, String additionalInformation) {
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.additionalInformation = additionalInformation;
    }

    public String getZipCode() {return zipCode;}

    public String getCity() {return city;}

    public String getStreet() {return street;}

    public String getNumber() {return number;}

    public String getAdditionalInformation() {return additionalInformation;}
}
