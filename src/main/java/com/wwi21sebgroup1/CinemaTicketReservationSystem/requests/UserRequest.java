package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;

import javax.persistence.ManyToOne;
import javax.swing.*;
import java.time.LocalDate;

public class UserRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String eMail;
    private JPasswordField password;
    private AddressRequest addressRequest;

    public UserRequest(Integer id, String firstName, String lastName, LocalDate dob, String eMail, JPasswordField password, AddressRequest addressRequest) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.eMail = eMail;
        this.password = password;
        this.addressRequest = addressRequest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    public AddressRequest getAddressRequest() {
        return addressRequest;
    }

    public void setAddressRequest(AddressRequest addressRequest) {
        this.addressRequest = addressRequest;
    }
}
