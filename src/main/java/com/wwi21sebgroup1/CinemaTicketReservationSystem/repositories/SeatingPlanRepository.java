package com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlan;
import org.springframework.data.repository.CrudRepository;

public interface SeatingPlanRepository extends CrudRepository<SeatingPlan, Integer> {
    void deleteByShowingId(Integer showingId);
}
