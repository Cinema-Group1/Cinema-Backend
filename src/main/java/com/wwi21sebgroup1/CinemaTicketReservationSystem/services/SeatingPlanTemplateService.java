package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.config.exceptions.InvalidRequestException;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.CinemaHall;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlanTemplate;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaHallRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanTemplateRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatingPlanTemplateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SeatingPlanTemplateService {
    @Autowired
    SeatingPlanTemplateRepository seatingPlanTemplateRepository;
    @Autowired
    SeatNumberRepository seatNumberRepository;
    @Autowired
    CinemaHallRepository cinemaHallRepository;

    public SeatingPlanTemplate addSeatingPlanTemplate(SeatingPlanTemplateRequest seatingPlanTemplateRequest)throws InvalidRequestException{
        SeatingPlanTemplate seatingPlanTemplate = processRequest(seatingPlanTemplateRequest);
        seatingPlanTemplateRepository.save(seatingPlanTemplate);
        return seatingPlanTemplate;
    }

    public Iterable<SeatingPlanTemplate> getAllSeatingPlanTemplates(){
        return seatingPlanTemplateRepository.findAll();
    }

    public Optional<SeatingPlanTemplate> getByCinemaHallId(Integer cinemaHallId){
        return seatingPlanTemplateRepository.findByCinemaHallId(cinemaHallId);
    }

    public SeatingPlanTemplate updateSeatingPlanTemplate(Integer id, SeatingPlanTemplateRequest seatingPlantemplateRequest)throws InvalidRequestException, NoSuchElementException{
        seatingPlanTemplateRepository.findById(id);
        SeatingPlanTemplate updatedSeatingPlanTemplate = processRequest(seatingPlantemplateRequest);
        updatedSeatingPlanTemplate.setId(id);
        seatingPlanTemplateRepository.save(updatedSeatingPlanTemplate);
        return updatedSeatingPlanTemplate;
    }

    public void deleteSeatingPlanTemplate(Integer id) throws NoSuchElementException {
        seatingPlanTemplateRepository.deleteById(id);
    }

    public SeatingPlanTemplate processRequest(SeatingPlanTemplateRequest seatingPlanTemplateRequest)throws InvalidRequestException, NoSuchElementException {
        if(seatingPlanTemplateRequest.getCinemaHallId() == 0 || seatingPlanTemplateRequest.getSeatsPerRow() == 0 ||
            seatingPlanTemplateRequest.getRows() == 0){
            throw new InvalidRequestException("SeatingPlanTemplateRequest");
        }
        CinemaHall cinemaHall = cinemaHallRepository.findById(seatingPlanTemplateRequest.getCinemaHallId()).get();
        SeatingPlanTemplate seatingPlanTemplate = new SeatingPlanTemplate(cinemaHall, seatingPlanTemplateRequest.getRows(), seatingPlanTemplateRequest.getSeatsPerRow());
        seatingPlanTemplateRepository.save(seatingPlanTemplate);
        for(int i = 1; i <= seatingPlanTemplateRequest.getRows(); i++){
            for(int j = 1; j <= seatingPlanTemplateRequest.getSeatsPerRow(); j++){
                SeatNumber seatNumber = new SeatNumber((char)(i + 64), (byte) j, seatingPlanTemplate);
                seatNumberRepository.save(seatNumber);
            }
        }
        return seatingPlanTemplate;
    }
}
