package com.wwi21sebgroup1.CinemaTicketReservationSystem;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.entities.Movie;
import com.wwi21sebgroup1.CinemaTicketReservationSystem.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
public class CinemaTicketReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaTicketReservationSystemApplication.class, args);
	}

}
