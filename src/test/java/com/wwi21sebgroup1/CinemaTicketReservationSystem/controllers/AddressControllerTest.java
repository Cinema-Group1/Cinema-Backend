package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.AddressRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.AddressService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {
    @Mock
    AddressService addressService;
    @InjectMocks
    AddressController addressController;
    static ResponseEntity<Object> expected;
    static ResponseEntity<Object> actual;
    static AddressRequest validRequestWithoutAdditionalInfo;
    static AddressRequest validRequestWithAdditionalInfo;
    static AddressRequest invalidRequest;
    static Address addressWithoutAdditionalInfo;
    static Address addressWithAdditionalInfo;

    @BeforeAll
    public static void beforeAll(){
        validRequestWithoutAdditionalInfo = new AddressRequest("12345", "Ludwigshafen", "Hauptstrasse", "123");
        validRequestWithAdditionalInfo = new AddressRequest("12345", "Ludwigshafen", "Hauptstrasse", "789", "Info");
        invalidRequest = new AddressRequest("", "Mannheim", "", "122");
        addressWithoutAdditionalInfo = new Address("12345", "Ludwigshafen", "Hauptstrasse", "123");
        addressWithoutAdditionalInfo.setId(1);
        addressWithAdditionalInfo = new Address("12345", "Ludwigshafen", "Hauptstrasse", "789", "Info");
        addressWithAdditionalInfo.setId(2);
    }

    @Nested
    class AddAddress{
        @Test
        public void t01ValidRequestWithoutAdditionalInfo() throws InvalidRequestException {
            expected = new ResponseEntity<>(addressWithoutAdditionalInfo, HttpStatus.ACCEPTED);
            when(addressService.addAddress(validRequestWithoutAdditionalInfo)).thenReturn(addressWithoutAdditionalInfo);
            actual = addressController.addAddress(validRequestWithoutAdditionalInfo);
            assertEquals(expected, actual);
        }
        @Test
        public void t02ValidRequestWithAdditionalInfo() throws InvalidRequestException {
            expected = new ResponseEntity<>(addressWithAdditionalInfo, HttpStatus.ACCEPTED);
            when(addressService.addAddress(validRequestWithAdditionalInfo)).thenReturn(addressWithAdditionalInfo);
            actual = addressController.addAddress(validRequestWithAdditionalInfo);
            assertEquals(expected, actual);
        }
        @Test
        public void t03InvalidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>( new InvalidRequestException("AddressRequest").toString(),
                                                                    HttpStatus.BAD_REQUEST);
            when(addressService.addAddress(invalidRequest)).thenThrow(new InvalidRequestException("AddressRequest"));
            actual = addressController.addAddress(invalidRequest);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class UpdateAddress{
        @Test
        public void t01ValidRequestWithoutAdditionalInfo() throws InvalidRequestException {
            expected = new ResponseEntity<>(addressWithoutAdditionalInfo, HttpStatus.ACCEPTED);
            when(addressService.updateAddress(2, validRequestWithoutAdditionalInfo)).thenReturn(addressWithoutAdditionalInfo);
            actual = addressController.updateAddress(2, validRequestWithoutAdditionalInfo);
            assertEquals(expected, actual);
        }
        @Test
        public void t02ValidRequestWithAdditionalInfo() throws InvalidRequestException {
            expected = new ResponseEntity<>(addressWithAdditionalInfo, HttpStatus.ACCEPTED);
            when(addressService.updateAddress(1, validRequestWithAdditionalInfo)).thenReturn(addressWithAdditionalInfo);
            actual = addressController.updateAddress(1, validRequestWithAdditionalInfo);
            assertEquals(expected, actual);
        }
        @Test
        public void t03InvalidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(new InvalidRequestException("AddressRequest").toString(), HttpStatus.BAD_REQUEST);
            when(addressService.updateAddress(1, invalidRequest)).thenThrow(new InvalidRequestException("AddressRequest"));
            actual = addressController.updateAddress(1, invalidRequest);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class deleteAddress{
        @Test
        public void t01AddressFound(){
            expected = new ResponseEntity<>("Successfully deleted Address with Id: " + 1, HttpStatus.ACCEPTED);
            actual = addressController.deleteAddress(1);
            assertEquals(expected, actual);
        }
        @Test
        public void t02AddressNotFound(){
            expected = new ResponseEntity<>(new NoSuchElementException().toString(), HttpStatus.NOT_FOUND);
            doThrow(new NoSuchElementException()).when(addressService).deleteAddress(10);
            actual = addressController.deleteAddress(10);
            assertEquals(expected, actual);
        }
    }
}
