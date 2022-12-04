package com.wwi21sebgroup1.CinemaTicketReservationSystem.movie;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    private String title;
    private int length;
    private LocalDate releasedDate;

    public Movie() {}

    public Movie(String title, int length, LocalDate releasedDate) {
        this.title = title;
        this.length = length;
        this.releasedDate = releasedDate;
    }

    public Movie(Integer id, String title, int length, LocalDate releasedDate) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.releasedDate = releasedDate;
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
