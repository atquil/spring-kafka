package com.atquil.springkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.atquil.springkafka.entity.Employee;

@SpringBootApplication
// * @EnableScheduling : It ensures that a background task executor is created. Without it, nothing gets scheduled.
@EnableScheduling
public class SpringKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaApplication.class, args);
	}

}
