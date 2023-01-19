package com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Showing;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ShowingRepository extends CrudRepository<Showing, Integer> {
    Iterable<Showing> findByMovieId(Integer movieId);
    Iterable<Showing> findByStartsAt(LocalDateTime startsAt);
}
