package com.wwi21sebgroup1.CinemaTicketReservationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
public class CinemaTicketReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaTicketReservationSystemApplication.class, args);
	}

}
