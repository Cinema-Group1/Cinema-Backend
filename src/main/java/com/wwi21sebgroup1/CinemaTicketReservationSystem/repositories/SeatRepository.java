package com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Seat;
import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends CrudRepository<Seat, Integer> {
    Iterable<Seat> findAllBySeatingPlanId(Integer id);
    void deleteAllBySeatingPlanId(Integer id);
}
