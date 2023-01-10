package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.User;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.UserRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public void addUser(@RequestBody UserRequest userRequest) {
        userRepository.save(transformRequestToObject(userRequest));
    }

    @PostMapping("/update:{oldUserId}")
    public void updateUser(@PathVariable Integer oldUserId, @RequestBody UserRequest userRequest){
        try{
            User updatedUser = transformRequestToObject(userRequest);
            updatedUser.setId(oldUserId);
            userRepository.save(updatedUser);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    @PostMapping("/delete:{oldUserId}")
    public void deleteUser(@PathVariable Integer oldUserId) {
        try {
            userRepository.deleteById(oldUserId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public User transformRequestToObject(UserRequest userRequest){
        return new User(userRequest.getFirstName(),
                        userRequest.getLastName(),
                        userRequest.getDob(),
                        userRequest.geteMail(),
                        userRequest.getPassword(),
                        new Address());
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getUsers() {
        return userRepository.findAll();
    }

}