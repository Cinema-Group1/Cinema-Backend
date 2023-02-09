package com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Iterable<Movie> findByGenreName(String genre);
}
