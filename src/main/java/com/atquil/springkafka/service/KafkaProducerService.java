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

    private final KafkaTemplate<String,PopulationList> kafkaTemplate;
    
    public void sendJsonMessage(PopulationList populationList){

        Message<PopulationList> message = MessageBuilder
        .withPayload(populationList)
        .setHeader(KafkaHeaders.TOPIC, "atquil_json")
        .build();

        kafkaTemplate.send(message);
    }
}
