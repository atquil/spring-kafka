package com.atquil.springkafka.component;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    
    // Group Id , Instance of same application then Common Listener
    @KafkaListener(topics = "atquil", groupId = "groupId")
    void listener(String data){
        System.out.println("Received Data : "+data);
    }

}
