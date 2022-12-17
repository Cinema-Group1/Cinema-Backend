package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.azure.core.annotation.Get;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;
    @GetMapping
    public @ResponseBody Iterable<Address> getAddresses(){
        return addressRepository.findAll();
    }
}
