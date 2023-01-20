package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

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

@Service
public class SeatingPlanTemplateService {
    @Autowired
    SeatingPlanTemplateRepository seatingPlanTemplateRepository;
    @Autowired
    SeatNumberRepository seatNumberRepository;
    @Autowired
    CinemaHallRepository cinemaHallRepository;

    public void addSeatingPlanTemplate(SeatingPlanTemplateRequest seatingPlanTemplateRequest){
        seatingPlanTemplateRepository.save(processRequest(seatingPlanTemplateRequest));

    }

    public Iterable<SeatingPlanTemplate> getAllSeatingPlanTemplates(){
        return seatingPlanTemplateRepository.findAll();
    }

    public SeatingPlanTemplate getByCinemaHallId(Integer cinemaHallId){
        return seatingPlanTemplateRepository.findByCinemaHallId(cinemaHallId).get();
    }

    public void updateSeatingPlanTemplate(Integer id, SeatingPlanTemplateRequest seatingPlantemplateRequest){
        try{
            SeatingPlanTemplate updatedSeatingPlanTemplate = processRequest(seatingPlantemplateRequest);
            updatedSeatingPlanTemplate.setId(id);
            seatingPlanTemplateRepository.save(updatedSeatingPlanTemplate);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void deleteSeatingPlanTemplate(Integer id) {
        try {
            seatingPlanTemplateRepository.deleteById(id);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public SeatingPlanTemplate processRequest(SeatingPlanTemplateRequest seatingPlanTemplateRequest){
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
