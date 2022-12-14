package com.wwi21sebgroup1.CinemaTicketReservationSystem.requests;

public class MovieRequest {
    private String title;
    private String imagePath;
    private String description;
    private int length;
    private String releasedDate;
    private String genreName;

    public MovieRequest() {
    }

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
        this.releasedDate = releasedDate;
        this.genreName = genreName;
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

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
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

