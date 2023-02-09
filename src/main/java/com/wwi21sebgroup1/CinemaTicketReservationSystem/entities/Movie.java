package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

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
    private LocalDate releaseDate;
    @ManyToOne
    @JoinColumn(name = "genre_name", referencedColumnName = "name")
    private Genre genre;


    public Movie() {}

    public Movie(String title, String imagePath, String description, int length, LocalDate releasedDate, Genre genre) {
        this.title = title;
        this.imagePath = imagePath;
        this.description = description;
        this.length = length;
        this.releaseDate = releasedDate;
        this.genre = genre;
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

    public void setTitle(String title) {this.title = title;}

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return length == movie.length && title.equals(movie.title) && imagePath.equals(movie.imagePath) && description.equals(movie.description) && releaseDate.equals(movie.releaseDate) && genre.equals(movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, imagePath, description, length, releaseDate, genre);
    }
}
