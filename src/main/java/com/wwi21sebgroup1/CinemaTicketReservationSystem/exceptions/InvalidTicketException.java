package com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions;

public class InvalidTicketException extends Exception {
    public InvalidTicketException(){
        super("Ticket was already used!");
    }
}