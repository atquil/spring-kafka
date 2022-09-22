package com.atquil.springkafka.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atquil.springkafka.entity.PopulationList;
import com.atquil.springkafka.service.KafkaProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class KafkaDemoController {
    
    private final KafkaProducerService kafkaProducersService;

    // http://localhost:8080/publish
    @PostMapping("/publish")
    public void publish(@RequestBody PopulationList populationList){
        kafkaProducersService.sendMessage(populationList.toString());
    }

}
