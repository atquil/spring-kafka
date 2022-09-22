package com.atquil.springkafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import com.atquil.springkafka.entity.PopulationList;

@SpringBootApplication
public class SpringKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaApplication.class, args);
	}

	/*
	 * Dependency to create json object : https://mvnrepository.com/artifact/org.json/json/20090211 
	 * - gradle
	 * // https://mvnrepository.com/artifact/org.json/json
		implementation 'org.json:json:20090211'	
	 */

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String,String> kafkaTemplate){
		//System.out.println("Producing Message for Topic atquil");
		
		return args -> {
			kafkaTemplate.send("atquil", "Sending this through atquil topic", "afasdf");
			}; 
	}
}
