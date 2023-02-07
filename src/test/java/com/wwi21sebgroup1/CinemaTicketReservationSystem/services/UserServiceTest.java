package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.User;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.AddressRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.UserRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.UserRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    AddressRepository addressRepository;
    @InjectMocks
    UserService userService;
    User expected;
    User actual;
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

    @Nested
    class AddUser{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = user;
            actual = userService.addUser(validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest(){
            assertThrows(InvalidRequestException.class, () -> userService.addUser(invalidRequest));
        }
    }

    @Nested
    class GetAllUsers{
        Iterable<User> expected;
        Iterable<User> actual;
        @Test
        public void t01NoUsersFound(){
            expected = List.of();
            when(userRepository.findAll()).thenReturn(List.of());
            actual = userService.getAllUsers();
            assertEquals(expected, actual);
        }
        @Test
        public void t02UserFound(){
            expected = List.of(user);
            when(userRepository.findAll()).thenReturn(List.of(user));
            actual = userService.getAllUsers();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class ValidateLogin{
        @Test
        public void t01loginSuccessful(){
            when(userRepository.findByEmail("karl@best.com")).thenReturn(Optional.of(user));
            assertTrue(userService.validateLogin("karl@best.com", "abc123"));
        }
        @Test
        public void t02WrongPassword(){
            when(userRepository.findByEmail("karl@best.com")).thenReturn(Optional.of(user));
            assertFalse(userService.validateLogin("karl@best.com", "xyz"));
        }
        @Test
        public void t03EMailNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                when(userRepository.findByEmail("abc@xyz.de")).thenThrow(new NoSuchElementException());
                userService.validateLogin("abc@xyz.de", "12345");
            });
        }
    }

    @Nested
    class UpdateUser{
        @Test
        public void t01UserFound() throws InvalidRequestException {
            expected = user;
            UserService userServiceSpy = spy(userService);
            doReturn(user).when(userServiceSpy).processRequest(validRequest);
            actual = userServiceSpy.updateUser(1, validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02UserNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                when(userRepository.findById(1)).thenThrow(new NoSuchElementException());
                userService.updateUser(1, validRequest);
            });
        }
        @Test
        public void t03ValidRequest() throws InvalidRequestException {
            expected = user;
            actual = userService.updateUser(1, validRequest);
        }
        @Test
        public void t04InvalidRequest(){
            assertThrows(InvalidRequestException.class, () -> userService.updateUser(1, invalidRequest));
        }
    }

    @Nested
    class DeleteUser{
        @Test
        public void t01UserFound(){
            userService.deleteUser(1);
        }
        @Test
        public void t02UserNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                //doThrow syntax is required because deleteById has return type void
                doThrow(new NoSuchElementException()).when(userRepository).deleteById(1);
                userService.deleteUser(1);
            });
        }
    }

    @Nested
    class ProcessRequest{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = user;
            actual = userService.processRequest(validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest(){
            assertThrows(InvalidRequestException.class, () -> userService.processRequest(invalidRequest));
        }
    }
}