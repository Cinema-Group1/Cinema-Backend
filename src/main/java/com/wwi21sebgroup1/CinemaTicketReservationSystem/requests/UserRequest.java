package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class UserRequest {
    private String firstName;
    private String lastName;
    private String dob;
    private String eMail;
    private String password;
    private String zipCode; //Postleitzahl
    private String city;
    private String street;
    private String number;
    private String additionalInformation;

    public UserRequest() {
    }

    public UserRequest(String firstName, String lastName, String dob, String eMail, String password, String zipCode, String city, String street, String number, String additionalInformation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.eMail = eMail;
        this.password = password;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.additionalInformation = additionalInformation;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }
}
