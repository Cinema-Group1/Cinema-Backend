package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class AddressRequest {
    private int id;
    private String zipCode; //Postleitzahl
    private String city;
    private String street;
    private String number;
    private String additionalInformation;

    public AddressRequest(){}

    public AddressRequest(Integer id, String zipCode, String city, String street, String number, String additionalInformation) {
        this.id = id;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.additionalInformation = additionalInformation;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getZipCode() {return zipCode;}

    public void setZipCode(String zipCode) {this.zipCode = zipCode;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street;}

    public String getNumber() {return number;}

    public void setNumber(String number) {this.number = number;}

    public String getAdditionalInformation() {return additionalInformation;}

    public void setAdditionalInformation(String additionalInformation) {this.additionalInformation = additionalInformation;}
}
