package com.wwi21sebgroup1.CinemaTicketReservationSystem;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.movie.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class CinemaTicketReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaTicketReservationSystemApplication.class, args);
	}

}
