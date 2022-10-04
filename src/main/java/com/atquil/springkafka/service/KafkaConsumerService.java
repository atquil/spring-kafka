package com.atquil.springkafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.atquil.springkafka.entity.Employee;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "streaming_data", groupId = "alpha")
    void KafkaListener(Employee data){
        System.out.println("**** Inside Consumer ****** ");
        System.out.println("Received Data :"+data);
    }

}
