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
        addressRepository.save(transformRequestToObject(addressRequest));
    }

    public void updateAddress(Integer oldAddressId, AddressRequest addressRequest) {
        try {
            Address updatedAddress = transformRequestToObject(addressRequest);
            updatedAddress.setId(oldAddressId);
            addressRepository.save(updatedAddress);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void deleteAddress(Integer oldAddressId){
        try{
            addressRepository.deleteById(oldAddressId);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Iterable<Address> getAddresses(){return addressRepository.findAll();}

    public static Address transformRequestToObject(AddressRequest addressRequest){
        return new Address (addressRequest.getZipCode(),
                addressRequest.getCity(),
                addressRequest.getStreet(),
                addressRequest.getNumber(),
                addressRequest.getAdditionalInformation());
    }
}
