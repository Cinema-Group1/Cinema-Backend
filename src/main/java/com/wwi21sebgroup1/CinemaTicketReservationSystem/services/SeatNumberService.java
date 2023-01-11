package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlanTemplate;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatNumberRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.SeatingPlanTemplateRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.SeatNumberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SeatNumberService {
    @Autowired
    SeatNumberRepository seatNumberRepository;
    @Autowired
    SeatingPlanTemplateRepository seatingPlanTemplateRepository;
    @Autowired
    SeatingPlanTemplateService seatingPlanTemplateService;

    public void addSeatNumber(SeatNumberRequest seatNumberRequest){
        addSeatNumber(transformRequestToObject(seatNumberRequest));
    }

    public void addSeatNumber(SeatNumber seatNumber){
        seatNumberRepository.save(seatNumber);
        seatingPlanTemplateService.addSeatNumber(seatNumber.getSeatingPlanTemplate().getId(), seatNumber);
    }

    public Iterable<SeatNumber> getAllSeatNumbers(){
        return seatNumberRepository.findAll();
    }

    public void updateSeatNumber(Integer oldSeatNumberId, SeatNumberRequest seatNumberRequest){
        try{
            SeatNumber updatedSeatNumber = transformRequestToObject(seatNumberRequest);
            updatedSeatNumber.setId(oldSeatNumberId);
            seatNumberRepository.save(updatedSeatNumber);
        }catch(NoSuchElementException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public SeatNumber transformRequestToObject(SeatNumberRequest seatNumberRequest){
        SeatingPlanTemplate seatingPlanTemplate = seatingPlanTemplateRepository.findById(seatNumberRequest.getSeatingPlanTemplateId()).get();
        return new SeatNumber(seatNumberRequest.getLine(), seatNumberRequest.getNumber(), seatingPlanTemplate);
    }
}
