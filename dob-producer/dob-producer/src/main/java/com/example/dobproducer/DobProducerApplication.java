package com.example.dobproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
@EnableKafka
public class DobProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DobProducerApplication.class, args);

		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);

	}

}
