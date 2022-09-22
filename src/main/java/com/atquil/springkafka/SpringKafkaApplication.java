package com.atquil.springkafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;
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

	

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String,PopulationList> kafkaTemplate){

		PopulationList populationList = new PopulationList("CommandLineRunner", "1");
		return args -> {
			kafkaTemplate.send("atquil_json",populationList);
			}; 
	}
}
