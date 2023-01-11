package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Cinema;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.CinemaHall;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlanTemplate;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaHallRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanTemplateRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.CinemaHallRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CinemaHallService {
    @Autowired
    private CinemaHallRepository cinemaHallRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SeatingPlanTemplateRepository seatingPlanTemplateRepository;

    public void addCinemaHall(CinemaHallRequest cinemaHallRequest){cinemaHallRepository.save(transformRequestToObject(cinemaHallRequest));}

    public void updateCinemaHall(Integer oldCinemaHallId, CinemaHallRequest cinemaHallRequest){
        try{
            CinemaHall updatedCinemaHall = transformRequestToObject(cinemaHallRequest);
            updatedCinemaHall.setId(oldCinemaHallId);
            cinemaHallRepository.save(updatedCinemaHall);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void deleteCinemaHall(Integer oldCinemaHallId){
        try {
            cinemaHallRepository.deleteById(oldCinemaHallId);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Iterable<CinemaHall> getCinemaHalls(){return cinemaHallRepository.findAll();}

    public CinemaHall transformRequestToObject(CinemaHallRequest cinemaHallRequest){
        Cinema cinema = cinemaRepository.findById(cinemaHallRequest.getCinemaId()).get();
        SeatingPlanTemplate seatingPlanTemplate = seatingPlanTemplateRepository.findById(cinemaHallRequest.getSeatingPlanTemplateId()).get();
        return new CinemaHall(  cinema,
                cinemaHallRequest.getName(),
                seatingPlanTemplate);
    }
}
