package com.atquil.springkafka.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.atquil.springkafka.entity.Employee;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    // * @EnableScheduling in main class so that, scheduling thread is provided
    private final KafkaTemplate<String,Employee> kafkaTemplate;
    int i = 1;

    @Scheduled(fixedRate = 5000) // Comment this to stop the scheduler and work with just streams
    // * scheduled method should not take any arguments and cannot return anything
    public void streamKafka() {
        String randomNameOfEmployee = RandomStringUtils.randomAlphabetic(5);
        Employee populationList = new Employee(randomNameOfEmployee, "Scheduler: "+i);

        publish( "streaming_data", populationList);
        i++;
    
    }
    public void publish(String topicName, Employee data) {
        System.out.println("******* Inside Producer **********");
        System.out.println("Publishing data :"+data);
        kafkaTemplate.send(topicName, data);
    }

}
