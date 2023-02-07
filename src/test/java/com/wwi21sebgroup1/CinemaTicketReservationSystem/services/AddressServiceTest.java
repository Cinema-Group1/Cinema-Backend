package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.AddressRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.AddressRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    @Mock
    AddressRepository addressRepository;
    @InjectMocks
    AddressService addressService;

    static AddressRequest request1;
    static AddressRequest request2;
    static AddressRequest request3;
    static Address address1;
    static Address address2;
    static Address address3;
    static Iterable<Address> addresses = new ArrayList<>();

    @BeforeAll
    public static void beforeAll(){
        request1 = new AddressRequest("12345", "Ludwigshafen", "Hauptstrasse", "123");
        request2 = new AddressRequest("12345", "Ludwigshafen", "Hauptstrasse", "456");
        request3 = new AddressRequest("12345", "Ludwigshafen", "Hauptstrasse", "789", "Info");
        address1 = new Address("12345", "Ludwigshafen", "Hauptstrasse", "123");
        address2 = new Address("12345", "Ludwigshafen", "Hauptstrasse", "456");
        address3 = new Address("12345", "Ludwigshafen", "Hauptstrasse", "789", "Info");
        addresses = new ArrayList<>(List.of(address1, address2, address3));
    }

    @Nested
    class AddAddress{
        @Test
        public void t01AddingMultipleAddresses(){
            try {
                addressService.addAddress(request1);
                addressService.addAddress(request2);
                addressService.addAddress(request3);
            }catch (Exception e){
                fail();
            }
        }
    }
    @Nested
    class UpdateAddress{
        @Test
        public void t01AddressFound(){
            try{
                when(addressRepository.findById(1)).thenReturn(Optional.of(address1));
                addressService.updateAddress(1, request1);
            }catch(Exception e){
                fail();
            }
        }

        @Test
        public void t02AddressNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                when(addressRepository.findById(3)).thenThrow(new NoSuchElementException());
                addressService.updateAddress(3, request3);
            });
        }
    }

    @Nested
    class DeleteAddress{
        @Test
        public void t01AddressFound(){
            try{
                addressService.deleteAddress(1);
            }catch(Exception e){
                fail();
            }
        }
        @Test
        public void t02AddressNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                //doThrow syntax is required because deleteById has return type void
                doThrow(new NoSuchElementException()).when(addressRepository).deleteById(1);
                addressService.deleteAddress(1);
            });
        }
    }

    @Nested
    class ProcessRequest{
        static AddressRequest invalidRequest = new AddressRequest("", "Mannheim", "", "34c");
        @Test
        public void t01WithAdditionalInfo() {
            try{
                Address actualAddress = addressService.processRequest(request1);
                actualAddress.setId(1);

                Address expectedAddress = address1;
                expectedAddress.setId(1);

                assertEquals(actualAddress,expectedAddress);
            }catch (InvalidRequestException invalidRequestException){
                fail();
            }
        }
        @Test
        public void t02WithoutAdditionalInfo() {
            try{
                Address actualAddress = addressService.processRequest(request3);
                actualAddress.setId(3);

                Address expectedAddress = address3;
                expectedAddress.setId(3);

                assertEquals(actualAddress,expectedAddress);
            }catch (InvalidRequestException invalidRequestException){
                fail();
            }
        }
        @Test
        public void t03InvalidRequest() {
            assertThrows(InvalidRequestException.class, () -> addressService.processRequest(invalidRequest));
        }
    }
}
