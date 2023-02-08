package com.wwi21sebgroup1.CinemaTicketReservationSystem.integration;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.controllers.ShowingController;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.*;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.ShowingRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ShowingCreationTest {
    @Autowired
    ShowingController showingController;
    @Autowired
    CinemaHallRepository cinemaHallRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    SeatingPlanRepository seatingPlanRepository;
    @Autowired
    SeatingPlanTemplateRepository seatingPlanTemplateRepository;
    @Autowired
    SeatRepository seatRepository;
    private String title = "Trigger Point Showing";
    private String startsAt = "2023-02-07T20:00";
    private String endsAt = "2023-02-07T22:00";
    private int movieId = 5;
    private int cinemaHallId = 20;
    private int pricePerSeat = 6;

    @Test
    public void t01ShowingCreation(){
        Movie movie = movieRepository.findById(5).get();
        CinemaHall cinemaHall = cinemaHallRepository.findById(20).get();
        showingController.addShowing(new ShowingRequest(title, startsAt, endsAt, movieId, cinemaHallId, pricePerSeat));
        Showing showing = new Showing(title, LocalDateTime.parse(startsAt), LocalDateTime.parse(endsAt), movie, cinemaHall, pricePerSeat);
        SeatingPlanTemplate seatingPlanTemplate = seatingPlanTemplateRepository.findByCinemaHallId(cinemaHallId).get();

        SeatingPlan expectedSeatingPlan = new SeatingPlan(showing);
        boolean seatingPlanFound = false;
        for(SeatingPlan seatingPlan : seatingPlanRepository.findAll()){
            if(seatingPlan.equals(expectedSeatingPlan)){
                seatingPlanFound = true;
            }
        }
        assertTrue(seatingPlanFound);

        List<Seat> expectedSeats = List.of(
                new Seat(pricePerSeat, false, expectedSeatingPlan, new SeatNumber('A', (byte)1, seatingPlanTemplate)),
                new Seat(pricePerSeat, false, expectedSeatingPlan, new SeatNumber('A', (byte)2, seatingPlanTemplate)),
                new Seat(pricePerSeat, false, expectedSeatingPlan, new SeatNumber('B', (byte)1, seatingPlanTemplate)),
                new Seat(pricePerSeat, false, expectedSeatingPlan, new SeatNumber('B', (byte)2, seatingPlanTemplate))
        );
        int expectedCount = expectedSeats.size();
        for(Seat actualSeat : seatRepository.findAll()){
            for(Seat expectedSeat : expectedSeats){
                if(actualSeat.equals(expectedSeat)){
                    expectedCount--;
                }
            }
        }
        assertTrue(expectedCount == 0);
    }
}
