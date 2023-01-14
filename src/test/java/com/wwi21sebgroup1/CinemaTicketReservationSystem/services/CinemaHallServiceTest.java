package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanTemplateRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.CinemaHallRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class CinemaHallServiceTest {
    @Mock
    private CinemaRepository cinemaRepository;
    @Mock
    private SeatingPlanTemplateRepository seatingPlanTemplateRepository;
    @InjectMocks
    CinemaHallService cinemaHallService;

    CinemaHall cinemaHall;
    CinemaHallRequest cinemaHallRequest;
    Cinema cinema;
    SeatingPlanTemplate seatingPlanTemplate;
    int id;

    public void setup(){
        String name = "Hall 1";
        cinema = new Cinema(new Address("01234","Beispielstadt","Beispielstraße","42","Keine Infos"));
        cinema.setId(1);
        List<SeatNumber> seats = new ArrayList<>();
        seatingPlanTemplate = new SeatingPlanTemplate();
        seatingPlanTemplate.setId(1);
        seats.add(new SeatNumber('A',(byte)1, seatingPlanTemplate));
        seats.add(new SeatNumber('B',(byte)2, seatingPlanTemplate));
        cinemaHall = new CinemaHall(cinema, name);
        cinemaHallRequest = new CinemaHallRequest(cinema.getId(), name);
    }

    @Test
    @DisplayName("CinemaHallRequest to CinemaHall: Transformation works as expected")
    public void transformRequestToObject(){
        setup();
        when(cinemaRepository.findById(cinemaHallRequest.getCinemaId())).thenReturn(Optional.of(cinema));

        CinemaHall actualCinemaHall = cinemaHallService.transformRequestToObject(cinemaHallRequest);
        actualCinemaHall.setId(id);

        CinemaHall expectedCinemaHall = cinemaHall;
        expectedCinemaHall.setId(id);

        assertEquals(actualCinemaHall,expectedCinemaHall);
    }
}
