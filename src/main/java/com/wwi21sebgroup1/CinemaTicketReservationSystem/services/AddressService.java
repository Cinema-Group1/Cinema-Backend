package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.AddressRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public void addAddress(AddressRequest addressRequest){
        addressRepository.save(processRequest(addressRequest));
    }

    public Iterable<Address> getAllAddresses(){
        return addressRepository.findAll();
    }

    public void updateAddress(Integer oldAddressId, AddressRequest addressRequest) throws NoSuchElementException{
        //Checks if there is an address with the specified Id and throws NoSuchElementException instead
        addressRepository.findById(oldAddressId);
        Address updatedAddress = processRequest(addressRequest);
        updatedAddress.setId(oldAddressId);
        addressRepository.save(updatedAddress);
    }

    public void deleteAddress(Integer oldAddressId) throws NoSuchElementException{
        addressRepository.deleteById(oldAddressId);
    }

    public Address processRequest(AddressRequest addressRequest){
        return new Address( addressRequest.getZipCode(),
                            addressRequest.getCity(),
                            addressRequest.getStreet(),
                            addressRequest.getNumber(),
                            addressRequest.getAdditionalInformation());
    }
}
