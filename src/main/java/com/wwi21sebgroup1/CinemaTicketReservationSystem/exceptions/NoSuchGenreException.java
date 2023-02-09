package com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions;

public class NoSuchGenreException extends Exception{
    public NoSuchGenreException(String genreName){
        super("Genre " + genreName + " does not exist!");
    }
}
