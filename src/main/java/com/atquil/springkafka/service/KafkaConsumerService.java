package com.atquil.springkafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    
    // Group Id , Instance of same application then Common Listener
    @KafkaListener(topics = "atquil", groupId = "groupId")
    void kafkaListener(String data){
        System.out.println("Received Data : "+data);
    }

}
