package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.AddressRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PutMapping("/add")
    public void addAddress(@RequestBody AddressRequest addressRequest) throws InvalidRequestException {
        if( addressRequest.getZipCode() == null || addressRequest.getCity() == null ||
            addressRequest.getStreet() == null || addressRequest.getNumber() == null){
            throw new InvalidRequestException("AddressRequest");
        }
        addressService.addAddress(addressRequest);
    }

    @PostMapping("/update:{oldAddressId}")
    public void updateAddress(@PathVariable Integer oldAddressId, @RequestBody AddressRequest addressRequest){
        addressService.updateAddress(oldAddressId, addressRequest);
    }

    @PostMapping("/delete:{oldAddressId}")
    public void deleteAddress(@PathVariable Integer oldAddressId){addressService.deleteAddress(oldAddressId);}

    @GetMapping("/all")
    public @ResponseBody Iterable<Address> getAddresses(){return addressService.getAllAddresses();}
}
