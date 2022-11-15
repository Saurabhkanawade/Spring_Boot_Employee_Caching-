package com.example.dobconsumer;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class DobConsumerApplication {

	@Value("${sendgrid.key}")
	private String key;

	@Bean
	public SendGrid getSendgrid()
	{
		return new SendGrid(key);
	}



	public static void main(String[] args) {
		SpringApplication.run(DobConsumerApplication.class, args);
	}


}
