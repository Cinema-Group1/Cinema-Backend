package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    private String title;
    private int length;
    private java.sql.Date releasedDate;
    @ManyToOne
    private Genre genre;

    public Movie() {}

    public Movie(String title, int length, java.sql.Date releasedDate, Genre genre) {
        this.title = title;
        this.length = length;
        this.releasedDate = releasedDate;
        this.genre = genre;
    }

    public long getId() {
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

    public java.sql.Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(java.sql.Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
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
