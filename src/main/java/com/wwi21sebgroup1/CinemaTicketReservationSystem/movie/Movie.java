package com.wwi21sebgroup1.CinemaTicketReservationSystem.movie;

import java.time.LocalDate;

public class Movie {
    private long id;
    private String title;
    private int length;
    private LocalDate releasedDate;

    public Movie() {}

    public Movie(String title, int length, LocalDate releasedDate) {
        this.title = title;
        this.length = length;
        this.releasedDate = releasedDate;
    }

    public Movie(long id, String title, int length, LocalDate releasedDate) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.releasedDate = releasedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", releasedDate=" + releasedDate +
                '}';
    }
}
