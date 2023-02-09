package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.AddressRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address addAddress(AddressRequest addressRequest) throws InvalidRequestException{
        Address address = processRequest(addressRequest);
        addressRepository.save(address);
        return address;
    }

    public Address updateAddress(Integer addressId, AddressRequest addressRequest) throws NoSuchElementException, InvalidRequestException{
        addressRepository.findById(addressId);
        Address updatedAddress = processRequest(addressRequest);
        updatedAddress.setId(addressId);
        addressRepository.save(updatedAddress);
        return updatedAddress;
    }

    public void deleteAddress(Integer oldAddressId) throws NoSuchElementException{
        addressRepository.deleteById(oldAddressId);
    }

    public Address processRequest(AddressRequest addressRequest) throws InvalidRequestException {
        if( addressRequest.getZipCode().isBlank() || addressRequest.getCity().isBlank() ||
                addressRequest.getStreet().isBlank() || addressRequest.getNumber().isBlank()){
            throw new InvalidRequestException("AddressRequest");
        }
        return new Address( addressRequest.getZipCode(),
                            addressRequest.getCity(),
                            addressRequest.getStreet(),
                            addressRequest.getNumber(),
                            addressRequest.getAdditionalInformation());
    }
}
