package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.User;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.InvalidRequestException;
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

    public User addUser(UserRequest userRequest) throws InvalidRequestException {
        User user = processRequest(userRequest);
        userRepository.save(user);
        return user;
    }

    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public @ResponseBody User getUserByEmail(String eMail) throws NoSuchElementException{
        return userRepository.findByEmail(eMail).get();
    }

    public boolean validateLogin(String eMail, String pwd) throws NoSuchElementException{
        User user = getUserByEmail(eMail);
        return user.getPassword().equals(pwd);
    }

    public User updateUser(Integer userId, UserRequest userRequest) throws InvalidRequestException, NoSuchElementException{
        userRepository.findById(userId);
        User updatedUser = processRequest(userRequest);
        updatedUser.setId(userId);
        userRepository.save(updatedUser);
        return updatedUser;
    }

    public void deleteUser(Integer userId) throws NoSuchElementException{
        userRepository.deleteById(userId);
    }

    public User processRequest(UserRequest userRequest) throws InvalidRequestException {
        if(userRequest.getFirstName() == null || userRequest.getLastName() == null || userRequest.getZipCode() == null ||
           userRequest.getStreet() == null || userRequest.getCity() == null || userRequest.getNumber() == null ||
           userRequest.getDob() == null || userRequest.getPassword() == null || userRequest.geteMail() == null){
            throw new InvalidRequestException("UserRequest");
        }
        Address address = new Address(userRequest.getZipCode(),
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
