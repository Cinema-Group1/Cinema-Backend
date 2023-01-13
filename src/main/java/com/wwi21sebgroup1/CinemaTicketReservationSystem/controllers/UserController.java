package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.User;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.AddressRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.UserRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.UserRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping("/add")
    public void addUser(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
    }

    @PutMapping("/update:{oldUserId}")
    public void updateUser(@PathVariable Integer oldUserId, @RequestBody UserRequest userRequest){
        userService.updateUser(oldUserId, userRequest);
    }

    @PostMapping("/delete:{oldUserId}")
    public void deleteUser(@PathVariable Integer oldUserId) {
        userService.deleteUser(oldUserId);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getUsers() {
        return userService.getUsers();
    }

}