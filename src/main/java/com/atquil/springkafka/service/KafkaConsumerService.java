package com.atquil.springkafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.atquil.springkafka.entity.Employee;

@Service
public class KafkaConsumerService {
    
    // Group Id , Instance of same application then Common Listener
    @KafkaListener(topics = "atquil_json", groupId = "alpha")
    void KafkaListener(Employee data){
        System.out.println("Received Json Data PopulationList[Name]: "+data.getName());
        System.out.println("Received Json Data PopulationList[id] : "+data.getId());
    }

}
