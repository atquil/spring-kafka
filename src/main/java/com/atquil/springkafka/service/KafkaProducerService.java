package com.atquil.springkafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.atquil.springkafka.entity.PopulationList;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

   private final KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessage(String message){
        kafkaTemplate.send("atquil",message);
    }
    

    // Produce Json

    private final KafkaTemplate<String,PopulationList> kafkaTemplate2;

    public void sendJsonMessage(PopulationList populationList){
        Message<PopulationList> message = MessageBuilder
        .withPayload(populationList)
        .setHeader(KafkaHeaders.TOPIC, "atquil")
        .build();

        kafkaTemplate2.send(message);
    }
}
