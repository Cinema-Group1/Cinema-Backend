package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class LoginRequest {
    private String eMail;
    private String pwd;

    public LoginRequest(String eMail, String pwd) {
        this.eMail = eMail;
        this.pwd = pwd;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
