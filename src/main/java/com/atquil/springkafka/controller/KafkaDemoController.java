package com.atquil.springkafka.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atquil.springkafka.entity.PopulationList;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class KafkaDemoController {
    
    private final KafkaTemplate<String , String> kafkaTemplate;

    @PostMapping("/send")
    public void kafkaController(@RequestBody PopulationList populationList){
        System.out.print(populationList);
        kafkaTemplate.send("atquil", populationList.toString());
    }

}
