package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class MovieRequest {
    private String title;
    private String imagePath;
    private String description;
    private int length;
    private String releaseDate;
    private String genreName;

    public MovieRequest(String title,
                        String imagePath,
                        String description,
                        int length,
                        String releasedDate,
                        String genreName) {
        this.title = title;
        this.length = length;
        this.imagePath = imagePath;
        this.description = description;
        this.releaseDate = releasedDate;
        this.genreName = genreName;
    }

    public String getImagePath() {
        return imagePath;
    }
    public String getDescription() {
        return description;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public String getTitle() {
        return title;
    }
    public int getLength() {
        return length;
    }
    public String getGenreName() {
        return genreName;
    }
}

