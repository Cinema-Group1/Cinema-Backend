package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.CinemaHall;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlanTemplate;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaHallRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanTemplateRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatingPlanTemplateRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SeatingPlanTemplateServiceTest {
    @Mock
    SeatingPlanTemplateRepository seatingPlanTemplateRepository;
    @Mock
    CinemaHallRepository cinemaHallRepository;
    @Mock
    SeatNumberRepository seatNumberRepository;
    @InjectMocks
    SeatingPlanTemplateService seatingPlanTemplateService;
    SeatingPlanTemplate expected;
    SeatingPlanTemplate actual;
    CinemaHall cinemaHall = new CinemaHall();
    int cinemaHallId = 1;
    int rows = 2;
    int seatsPerRow = 2;
    SeatingPlanTemplateRequest validRequest = new SeatingPlanTemplateRequest(rows, seatsPerRow, cinemaHallId);
    SeatingPlanTemplateRequest invalidRequest = new SeatingPlanTemplateRequest(0, 0, 0);
    SeatingPlanTemplate seatingPlanTemplate = new SeatingPlanTemplate(cinemaHall, rows, seatsPerRow);


    @Nested
    class AddSeatingPlanTemplate{
        @Test
        public void t01ValidRequest() throws InvalidRequestException {
            expected = seatingPlanTemplate;
            when(cinemaHallRepository.findById(cinemaHallId)).thenReturn(Optional.of(cinemaHall));
            actual = seatingPlanTemplateService.addSeatingPlanTemplate(validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t02InvalidRequest(){
            assertThrows(InvalidRequestException.class, () -> seatingPlanTemplateService.addSeatingPlanTemplate(invalidRequest));
        }

        @Test
        public void t03CinemaHallNotFound(){
            when(cinemaHallRepository.findById(cinemaHallId)).thenThrow(new NoSuchElementException());
            assertThrows(NoSuchElementException.class, () -> seatingPlanTemplateService.addSeatingPlanTemplate(validRequest));
        }
    }

    @Nested
    class GetAllSeatingPlanTemplates{
        Iterable<SeatingPlanTemplate> expected;
        Iterable<SeatingPlanTemplate> actual;
        @Test
        public void t01NoSeatingPlanTemplatesFound(){
            expected = List.of();
            when(seatingPlanTemplateRepository.findAll()).thenReturn(List.of());
            actual = seatingPlanTemplateService.getAllSeatingPlanTemplates();
            assertEquals(expected, actual);
        }
        @Test
        public void t02SeatingPlanTemplateFound(){
            expected = List.of(seatingPlanTemplate);
            when(seatingPlanTemplateRepository.findAll()).thenReturn(List.of(seatingPlanTemplate));
            actual = seatingPlanTemplateService.getAllSeatingPlanTemplates();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetSeatingPlanTemplatesByCinemaHall{
        SeatingPlanTemplate expected;
        SeatingPlanTemplate actual;
        @Test
        public void t01NoSuchCinemaHall(){
            when(seatingPlanTemplateRepository.findByCinemaHallId(cinemaHallId)).thenThrow(new NoSuchElementException());
            assertThrows(NoSuchElementException.class, () -> seatingPlanTemplateService.getByCinemaHallId(cinemaHallId));
        }
        @Test
        public void t03SeatingPlanTemplatesFound() {
            when(seatingPlanTemplateRepository.findByCinemaHallId(cinemaHallId)).thenReturn(Optional.of(seatingPlanTemplate));
            expected = seatingPlanTemplate;
            actual = seatingPlanTemplateService.getByCinemaHallId(cinemaHallId).get();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class UpdateSeatingPlanTemplate{
        @Test
        public void t01SeatingPlanTemplateFound() throws InvalidRequestException {
            expected = seatingPlanTemplate;
            SeatingPlanTemplateService seatingPlanTemplateServiceSpy = spy(seatingPlanTemplateService);
            doReturn(seatingPlanTemplate).when(seatingPlanTemplateServiceSpy).processRequest(validRequest);
            when(seatingPlanTemplateRepository.findById(1)).thenReturn(Optional.of(seatingPlanTemplate));
            actual = seatingPlanTemplateServiceSpy.updateSeatingPlanTemplate(1, validRequest);
            assertEquals(expected, actual);
        }
        @Test
        public void t03InvalidRequest() throws InvalidRequestException {
            SeatingPlanTemplateService seatingPlanTemplateServiceSpy = spy(seatingPlanTemplateService);
            doThrow(new InvalidRequestException("SeatingPlanTemplateRequest")).when(seatingPlanTemplateServiceSpy).processRequest(invalidRequest);
            assertThrows(InvalidRequestException.class, () -> seatingPlanTemplateServiceSpy.updateSeatingPlanTemplate(1, invalidRequest));
        }
    }

    @Nested
    class DeleteSeatingPlanTemplate{
        @Test
        public void t01SeatingPlanTemplateFound(){
            seatingPlanTemplateService.deleteSeatingPlanTemplate(1);
        }
        @Test
        public void t02SeatingPlanTemplateNotFound(){
            assertThrows(NoSuchElementException.class, () -> {
                //doThrow syntax is required because deleteById has return type void
                doThrow(new NoSuchElementException()).when(seatingPlanTemplateRepository).deleteById(1);
                seatingPlanTemplateService.deleteSeatingPlanTemplate(1);
            });
        }
    }

    @Nested
    class ProcessRequest{}

}
