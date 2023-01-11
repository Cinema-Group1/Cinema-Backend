package com.wwi21sebgroup1.CinemaTicketReservationSystem.services;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Address;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Cinema;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.AddressRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.CinemaRepository;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.requests.CinemaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private AddressRepository addressRepository;

    public void addCinema(CinemaRequest cinemaRequest){cinemaRepository.save(transformRequestToObject(cinemaRequest));}

    public void updateCinema(Integer oldCinemaId, CinemaRequest cinemaRequest) {
        try {
            Cinema updatedCinema = transformRequestToObject(cinemaRequest);
            updatedCinema.setId(oldCinemaId);
            cinemaRepository.save(updatedCinema);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void deleteCinema(Integer oldCinemaId) {
        try {
            cinemaRepository.deleteById(oldCinemaId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public Iterable<Cinema> getCinemas(){return cinemaRepository.findAll();}

    public Cinema transformRequestToObject(CinemaRequest cinemaRequest){
        Address address = addressRepository.findById(cinemaRequest.getAddressId()).get();
        return new Cinema(address);
    }
}
