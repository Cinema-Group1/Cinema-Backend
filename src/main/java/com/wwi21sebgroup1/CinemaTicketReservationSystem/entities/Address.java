package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    //Postleitzahl
    private String zipCode;
    private String city;
    private String street;
    private String number;
    private String additionalInformation;
}
