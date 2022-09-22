package com.atquil.springkafka.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atquil.springkafka.entity.PopulationList;
import com.atquil.springkafka.service.KafkaProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class KafkaDemoController {
    
    private final KafkaProducerService kafkaProducersService;


    @PostMapping("/publish")
    public void publish(@RequestBody PopulationList populationList){
        kafkaProducersService.sendJsonMessage(populationList);
    }

}
