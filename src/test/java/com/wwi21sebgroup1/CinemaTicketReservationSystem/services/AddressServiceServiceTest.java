package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.AddressRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.MovieRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceServiceTest {
    AddressRequest addressRequest;
    Address address;
    int id;

    public void setup(){
        String zipCode = "68199";
        String city = "Mannheim";
        String street = "Casterfeldstraße";
        String number = "22a";
        String additionalInformation = "Justin hat nen kleinen Freund";
        id = 1;
        addressRequest = new AddressRequest(zipCode, city, street,number,additionalInformation);
        address = new Address(zipCode, city, street, number,additionalInformation);
    }
    @Test
    @DisplayName("Transformation works as expected")
    public void t01TransformRequestToObject() {
        setup();
        Address actualAddress = AddressService.transformRequestToObject(addressRequest);
        actualAddress.setId(id);
        Address expectedAddress = address;
        expectedAddress.setId(id);
        assertEquals(actualAddress,expectedAddress);
    }
}
