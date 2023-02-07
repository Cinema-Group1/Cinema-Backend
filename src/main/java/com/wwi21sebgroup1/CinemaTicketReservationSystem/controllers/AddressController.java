package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.AddressRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PutMapping("/add")
    public ResponseEntity<Object> addAddress(@RequestBody AddressRequest addressRequest){
        try{
            return new ResponseEntity<>(addressService.addAddress(addressRequest), HttpStatus.ACCEPTED);
        }catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update:{oldAddressId}")
    public ResponseEntity<Object> updateAddress(@PathVariable Integer oldAddressId, @RequestBody AddressRequest addressRequest){
        try {
            return new ResponseEntity<>(addressService.updateAddress(oldAddressId, addressRequest), HttpStatus.ACCEPTED);
        }catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }
        catch(NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/delete:{oldAddressId}")
    public ResponseEntity<Object> deleteAddress(@PathVariable Integer oldAddressId){
        try {
            addressService.deleteAddress(oldAddressId);
            return new ResponseEntity<>("Successfully deleted Address with Id: " + oldAddressId, HttpStatus.ACCEPTED);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }
}
