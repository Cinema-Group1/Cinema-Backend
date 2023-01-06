package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

import java.sql.Date;

public class MovieRequest {
    private int id;
    private String title;
    private int length;
    private String releasedDate;
    private String genreName;

    public MovieRequest(){}

    public MovieRequest(Integer id, String title, int length, String releasedDate, String genreName) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.releasedDate = releasedDate;
        this.genreName = genreName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getReleasedDateString() {
        return releasedDate;
    }

    public void setReleasedDateString(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}

