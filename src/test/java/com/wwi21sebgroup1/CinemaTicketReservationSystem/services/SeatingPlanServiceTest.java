package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlanTemplate;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatingPlanTemplateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SeatingPlanServiceTest {
    @Mock
    SeatNumberRepository seatNumberRepository;
    @InjectMocks
    SeatingPlanTemplateService seatingPlanTemplateService;

    SeatingPlanTemplate seatingPlanTemplate;
    SeatingPlanTemplateRequest seatingPlanTemplateRequest;
    int rows;
    int seatsPerRow;
    List<SeatNumber> seats;
    int id;

    public void setup(){
        rows = 3;
        seatsPerRow = 2;
        seats = new ArrayList<>();
        for(int i = 1; i <= rows; i++){
            for(int j = 1; j <= seatsPerRow; j++){
                SeatNumber seatNumber = new SeatNumber((char)(i + 64), (byte) j);
                seats.add(seatNumber);
            }
        }
        seatingPlanTemplate = new SeatingPlanTemplate(seats);
        seatingPlanTemplateRequest = new SeatingPlanTemplateRequest(rows,seatsPerRow);
    }
    @Test
    @DisplayName("SeatingPlanTemplateRequest to SeatingPlan: Transformation works as expected")
    public void transformRequestToObject() {
        setup();

        SeatingPlanTemplate actualSeatingPlanTemplate = seatingPlanTemplateService.transformRequestToObject(seatingPlanTemplateRequest);
        actualSeatingPlanTemplate.setId(1);

        SeatingPlanTemplate expectedSeatingPlanTemplate = seatingPlanTemplate;
        expectedSeatingPlanTemplate.setId(1);

        assertEquals(expectedSeatingPlanTemplate, actualSeatingPlanTemplate);
    }
}
