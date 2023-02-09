package com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    void deleteByShowingId(Integer showingId);
    Iterable<Booking> findByUserId(Integer userId);
}
