package com.ReservationComponent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@EnableEurekaClient
public class ReservationComponentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationComponentApplication.class, args);
	}

}
