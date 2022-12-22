package com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    //The name of the method defines which field of the table should be queried.
    //There is no further implementation necessary. In this case, the queried field is
    //genre_name, so the method has to be called findByGenreName
    //See also: https://www.baeldung.com/spring-data-derived-queries
    Iterable<Movie> findByGenreName(String genre);
}
