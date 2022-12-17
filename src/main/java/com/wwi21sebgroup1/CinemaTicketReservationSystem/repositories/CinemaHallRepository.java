package com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Cinema;
import org.springframework.data.repository.CrudRepository;

public interface CinemaHallRepository extends CrudRepository<Cinema, Integer> {
}
