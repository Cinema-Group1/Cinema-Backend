package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.AddressRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/add")
    public void addAddress(@RequestBody AddressRequest addressRequest){
        addressRepository.save(transformRequestToObject(addressRequest));
    }

    @PostMapping("/update:{oldAddressId}")
    public void updateAddress(@PathVariable Integer oldAddressId, @RequestBody AddressRequest addressRequest) {
        try {
            Address updatedAddress = transformRequestToObject(addressRequest);
            updatedAddress.setId(oldAddressId);
            addressRepository.save(updatedAddress);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    @PostMapping("/delete:{oldAddressId}")
    public void deleteAddress(@PathVariable Integer oldAddressId){
        try{
            addressRepository.deleteById(oldAddressId);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Address transformRequestToObject(AddressRequest addressRequest){
        return new Address (addressRequest.getZipCode(),
                addressRequest.getCity(),
                addressRequest.getStreet(),
                addressRequest.getNumber(),
                addressRequest.getAdditionalInformation());
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Address> getAddresses(){
        return addressRepository.findAll();
    }
}
