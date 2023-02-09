package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.LoginRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.UserRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody UserRequest userRequest) {
        try{
            return new ResponseEntity<>(userService.addUser(userRequest), HttpStatus.ACCEPTED);
        }catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/validateLogin")
    public ResponseEntity<Object> validateLogin(@RequestBody LoginRequest loginRequest){
        try{
            if(userService.validateLogin(loginRequest.getEMail(), loginRequest.getPwd())){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
            }
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update:{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer userId, @RequestBody UserRequest userRequest){
        try{
            return new ResponseEntity<>(userService.updateUser(userId, userRequest), HttpStatus.ACCEPTED);
        }catch (InvalidRequestException invalidRequestException){
            return new ResponseEntity<>(invalidRequestException.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete:{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer userId) {
        try{
            userService.deleteUser(userId);
            return new ResponseEntity<>("Successfully deleted User with Id: " + userId, HttpStatus.ACCEPTED);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(noSuchElementException.toString(), HttpStatus.NOT_FOUND);
        }
    }
}