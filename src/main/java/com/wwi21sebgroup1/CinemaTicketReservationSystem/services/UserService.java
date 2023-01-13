package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.User;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.AddressRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.UserRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    public void addUser(UserRequest userRequest) {
        userRepository.save(transformRequestToObject(userRequest));
    }

    public void updateUser(Integer oldUserId, UserRequest userRequest){
        try{
            User updatedUser = transformRequestToObject(userRequest);
            updatedUser.setId(oldUserId);
            userRepository.save(updatedUser);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void deleteUser(Integer oldUserId) {
        try {
            userRepository.deleteById(oldUserId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public @ResponseBody Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User transformRequestToObject(UserRequest userRequest){
        Address address = new Address(  userRequest.getZipCode(),
                userRequest.getCity(),
                userRequest.getStreet(),
                userRequest.getNumber(),
                userRequest.getAdditionalInformation());
        addressRepository.save(address);
        return new User(userRequest.getFirstName(),
                userRequest.getLastName(),
                LocalDate.parse(userRequest.getDob()),
                userRequest.geteMail(),
                userRequest.getPassword(),
                address);
    }
}
