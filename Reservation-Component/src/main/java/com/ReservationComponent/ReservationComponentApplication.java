package com.ReservationComponent;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition
public class ReservationComponentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationComponentApplication.class, args);

	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
