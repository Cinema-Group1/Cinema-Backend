package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlanTemplate;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanTemplateRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatingPlanTemplateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SeatingPlanTemplateService {
    @Autowired
    SeatingPlanTemplateRepository seatingPlanTemplateRepository;
    @Autowired
    SeatNumberRepository seatNumberRepository;

    public void addSeatingPlanTemplate(SeatingPlanTemplateRequest seatingPlanTemplateRequest){
        seatingPlanTemplateRepository.save(transformRequestToObject(seatingPlanTemplateRequest));

    }

    public Iterable<SeatingPlanTemplate> getAllSeatingPlanTemplates(){
        return seatingPlanTemplateRepository.findAll();
    }

    public void updateSeatingPlanTemplate(Integer id, SeatingPlanTemplateRequest seatingPlantemplateRequest){
        try{
            SeatingPlanTemplate updatedSeatingPlanTemplate = transformRequestToObject(seatingPlantemplateRequest);
            updatedSeatingPlanTemplate.setId(id);
            seatingPlanTemplateRepository.save(updatedSeatingPlanTemplate);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void addSeatNumber(Integer seatingPlanTemplateId, SeatNumber seatNumber){
        SeatingPlanTemplate seatingPlanTemplate = seatingPlanTemplateRepository.findById(seatingPlanTemplateId).get();
        seatingPlanTemplate.getSeatNumbers().add(seatNumber);
        seatingPlanTemplateRepository.save(seatingPlanTemplate);
    }

    public void deleteSeatingPlanTemplate(Integer id) {
        try {
            seatingPlanTemplateRepository.deleteById(id);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public SeatingPlanTemplate transformRequestToObject(SeatingPlanTemplateRequest seatingPlanTemplateRequest){
        SeatingPlanTemplate seatingPlanTemplate = new SeatingPlanTemplate(new ArrayList<>());
        seatingPlanTemplateRepository.save(seatingPlanTemplate);
        for(int i = 1; i <= seatingPlanTemplateRequest.getRows(); i++){
            for(int j = 1; j <= seatingPlanTemplateRequest.getSeatsPerRow(); j++){
                SeatNumber seatNumber = new SeatNumber((char)(i + 64), (byte) j);
                seatNumberRepository.save(seatNumber);
                seatingPlanTemplate.getSeatNumbers().add(seatNumber);
            }
        }
        return new SeatingPlanTemplate();
    }
}
