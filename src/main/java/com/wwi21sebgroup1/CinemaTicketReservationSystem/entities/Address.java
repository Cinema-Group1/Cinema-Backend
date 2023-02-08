package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;
    private String zipCode; //Postleitzahl
    private String city;
    private String street;
    private String number;
    private String additionalInformation;

    public Address(){}

    public Address(String zipCode, String city, String street, String number) {
        super();
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.additionalInformation = "";
    }

    public Address(String zipCode, String city, String street, String number, String additionalInformation) {
        super();
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.additionalInformation = additionalInformation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zipCode.equals(address.zipCode) &&
               city.equals(address.city) &&
               street.equals(address.street) &&
               number.equals(address.number) &&
               additionalInformation.equals(address.additionalInformation);
    }

    @Override
    public int hashCode() {return Objects.hash(id, zipCode, city, street, number, additionalInformation);}
}
