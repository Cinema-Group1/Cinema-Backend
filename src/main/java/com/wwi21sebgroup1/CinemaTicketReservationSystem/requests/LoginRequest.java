package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class LoginRequest {
    private final String eMail;
    private final String pwd;

    public LoginRequest(String eMail, String pwd) {
        this.eMail = eMail;
        this.pwd = pwd;
    }

    public String getEMail() {
        return eMail;
    }

    public String getPwd() {
        return pwd;
    }
}
