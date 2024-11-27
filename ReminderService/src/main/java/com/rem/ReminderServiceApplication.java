package com.rem;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class ReminderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReminderServiceApplication.class, args);
	}

}
