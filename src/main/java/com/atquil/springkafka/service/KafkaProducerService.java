package com.atquil.springkafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.atquil.springkafka.entity.Employee;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String,Employee> kafkaTemplate;
    int i = 1;
    @Scheduled(fixedRate = 5000)
    public void streamKafka() { // No arguments for the method. 
        Employee populationList = new Employee("Streaming Service Mimic ", "Id: "+i);
        i++;
        sendMessage( "atquil_json", populationList);
    
  }
  public void sendMessage(String topicName, Employee populationList) {
    kafkaTemplate.send(topicName, populationList);
  }


}
