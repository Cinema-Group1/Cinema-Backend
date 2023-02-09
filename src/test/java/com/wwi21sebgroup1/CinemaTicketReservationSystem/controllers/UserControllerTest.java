package com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.User;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.LoginRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.UserRequest;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.services.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;
    
    String firstName = "Karl";
    String lastName = "Best";
    String dob = "2000-01-01";
    String eMail = "karl@best.com";
    String password = "abc123";
    String zipCode = "12345";
    String city = "Mannheim";
    String street = "Hauptstrasse";
    String number = "12";
    String additionalInformation = "Wohnung 321";

    Address address = new Address(zipCode, city, street, number, additionalInformation);
    User user = new User(firstName, lastName, LocalDate.parse(dob), eMail, password, address);
    UserRequest validRequest = new UserRequest(firstName, lastName, dob, eMail, password,
                                              zipCode, city, street, number, additionalInformation);
    UserRequest invalidRequest = new UserRequest();
    ResponseEntity<Object> expected;
    ResponseEntity<Object> actual;

    @Nested
    class AddUser{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(user, HttpStatus.ACCEPTED);
            when(userService.addUser(validRequest)).thenReturn(user);
            actual = userController.addUser(validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>( new InvalidRequestException("UserRequest").toString(), HttpStatus.BAD_REQUEST);
            when(userService.addUser(invalidRequest)).thenThrow(new InvalidRequestException("UserRequest"));
            actual = userController.addUser(invalidRequest);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetAllUsers{
        @Test
        public void t01NoUsersFound(){
            expected = new ResponseEntity<>(List.of(), HttpStatus.ACCEPTED);
            when(userService.getAllUsers()).thenReturn(List.of());
            actual = userController.getAllUsers();
            assertEquals(expected, actual);
        }
        @Test
        public void t02UserFound(){
            expected = new ResponseEntity<>(List.of(user), HttpStatus.ACCEPTED);
            when(userService.getAllUsers()).thenReturn(List.of(user));
            actual = userController.getAllUsers();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class ValidateLogin{
        @Test
        public void t01loginSuccessful(){
            expected = new ResponseEntity<>(true, HttpStatus.OK);
            when(userService.validateLogin("karl@best.com", "abc123")).thenReturn(true);
            actual = userController.validateLogin(new LoginRequest("karl@best.com", "abc123"));
            assertEquals(expected, actual);
        }
        @Test
        public void t02WrongPassword(){
            expected = new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
            when(userService.validateLogin("karl@best.com", "xyz")).thenReturn(false);
            actual = userController.validateLogin(new LoginRequest("karl@best.com", "xyz"));
            assertEquals(expected, actual);
        }
        @Test
        public void t03EMailNotFound(){
            expected = new ResponseEntity<>(new NoSuchElementException().toString(), HttpStatus.NOT_FOUND);
            when(userService.validateLogin("abc@xyz.com", "abc123")).thenThrow(new NoSuchElementException());
            actual = userController.validateLogin(new LoginRequest("abc@xyz.com", "abc123"));
            assertEquals(expected, actual);
        }
    }

    @Nested
    class UpdateUser{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(user, HttpStatus.ACCEPTED);
            when(userService.updateUser(1, validRequest)).thenReturn(user);
            actual = userController.updateUser(1, validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest() throws InvalidRequestException {
            expected = new ResponseEntity<>(new InvalidRequestException("UserRequest").toString(), HttpStatus.BAD_REQUEST);
            when(userService.updateUser(1, invalidRequest)).thenThrow(new InvalidRequestException("UserRequest"));
            actual = userController.updateUser(1, invalidRequest);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class deleteUser{
        @Test
        public void t01UserFound(){
            expected = new ResponseEntity<>("Successfully deleted User with Id: " + 1, HttpStatus.ACCEPTED);
            actual = userController.deleteUser(1);
            assertEquals(expected, actual);
        }
        @Test
        public void t02UserNotFound(){
            expected = new ResponseEntity<>(new NoSuchElementException().toString(), HttpStatus.NOT_FOUND);
            doThrow(new NoSuchElementException()).when(userService).deleteUser(1);
            actual = userController.deleteUser(1);
            assertEquals(expected, actual);
        }
    }
}
