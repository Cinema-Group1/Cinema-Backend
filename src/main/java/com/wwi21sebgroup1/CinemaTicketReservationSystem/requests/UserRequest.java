package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import javax.swing.*;
import java.time.LocalDate;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
