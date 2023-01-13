package com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatNumberRepository extends CrudRepository<SeatNumber, Integer> {
    Iterable<SeatNumber> findAllBySeatingPlanTemplateId(Integer id);
}
