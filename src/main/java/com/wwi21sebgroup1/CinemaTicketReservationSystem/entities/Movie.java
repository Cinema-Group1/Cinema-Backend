package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.sql.Date;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String imagePath;
    private String description;
    private int length;
    private Date releasedDate;
    @ManyToOne
    @JoinColumn(name = "genre_name", referencedColumnName = "name")
    private Genre genre;


    public Movie() {}

    public Movie(String title, String imagePath, String description, int length, Date releasedDate, Genre genre) {
        this.title = title;
        this.imagePath = imagePath;
        this.description = description;
        this.length = length;
        this.releasedDate = releasedDate;
        this.genre = genre;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
