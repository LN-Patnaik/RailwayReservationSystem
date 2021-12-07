package com.TrainComponent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TrainComponentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainComponentApplication.class, args);
	}

}
