package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Cinema;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.AddressRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.CinemaRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CinemaServiceTest {

    @Mock
    AddressRepository addressRepository;
    @InjectMocks
    CinemaService cinemaService;

    Cinema cinema;
    CinemaRequest cinemaRequest;
    int id;
    Address address;

    public void setup(){
        address = new Address("06069","Offenbach", "Taunusstraße", "1", "echt gefährlich da");
        cinema = new Cinema(address);
        cinemaRequest = new CinemaRequest(address.getId());
    }

    @Test
    @DisplayName("Transformation works as expected")
    public void t01TransformRequestToObject() {
        setup();
        when(addressRepository.findById(cinemaRequest.getAddressId())).thenReturn(Optional.of(address));

        Cinema actualCinema = cinemaService.transformRequestToObject(cinemaRequest);
        actualCinema.setId(id);

        Cinema expectedCinema = cinema;
        expectedCinema.setId(id);

        assertEquals(actualCinema,expectedCinema);
    }
    @Test
    @DisplayName("Address was not found")
    public void t02TransformRequestToObject(){
        assertThrows(NoSuchElementException.class, () -> {
            setup();

            Cinema actualCinema = cinemaService.transformRequestToObject(cinemaRequest);
            actualCinema.setId(id);

            Cinema expectedCinema = cinema;
            expectedCinema.setId(id);
        });
    }
}
