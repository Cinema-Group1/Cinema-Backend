package com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.SeatingPlanTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SeatingPlanTemplateRepository extends CrudRepository<SeatingPlanTemplate, Integer> {
    Optional<SeatingPlanTemplate> findByCinemaHallId(Integer id);
}
