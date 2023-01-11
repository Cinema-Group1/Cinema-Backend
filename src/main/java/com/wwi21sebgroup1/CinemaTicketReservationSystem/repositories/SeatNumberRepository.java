package com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import org.springframework.data.repository.CrudRepository;

public interface SeatNumberRepository extends CrudRepository<SeatNumber, Integer> {
    public Iterable<SeatNumber> findAllBySeatingPlanTemplateId(Integer id);
}
