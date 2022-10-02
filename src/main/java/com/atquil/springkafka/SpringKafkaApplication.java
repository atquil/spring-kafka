package com.atquil.springkafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.atquil.springkafka.entity.Employee;

@SpringBootApplication
@EnableScheduling // * The @EnableScheduling annotation ensures that a background task executor is created. Without it, nothing gets scheduled.
public class SpringKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaApplication.class, args);
	}

	

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String,Employee> kafkaTemplate){

		Employee populationList = new Employee("CommandLineRunner", "1");
		return args -> {
			kafkaTemplate.send("atquil_json",populationList);
			}; 
	}
}
