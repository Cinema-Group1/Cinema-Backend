package com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
    Genre findByName(String genreName);
}
