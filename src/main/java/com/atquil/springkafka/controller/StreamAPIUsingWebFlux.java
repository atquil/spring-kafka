package com.atquil.springkafka.controller;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.atquil.springkafka.entity.Employee;
import com.atquil.springkafka.service.KafkaProducerService;

import lombok.RequiredArgsConstructor;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

// Spring WebFlux: Spring WebFlux supports fully non-blocking reactive streams
@RestController
@RequiredArgsConstructor
@RequestMapping("kafka")
public class StreamAPIUsingWebFlux {

    private final KafkaProducerService kafkaProducerService;

    // *********************  API to create Stream of Data ***********************************

    // MediaType.APPLICATION_STREAM_JSON_VALUE : If you want to see stream in browser
    @GetMapping(value = "/stream/create-employee-data" , produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Employee> streamJsonObjects() {
        String randomName = RandomStringUtils.randomAlphabetic(5).toUpperCase();
      return Flux.interval(Duration.ofSeconds(2)).map(i -> new Employee(randomName, i.toString()));
    }


    // *********************  API to Read Stream of Data and publish to kafka ***********************************
    @GetMapping(value  = "/stream/read-and-publish", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public void streamDataToKafka(){

        
        Flux<Employee> employeeStream =    WebClient.create()
            .get().uri("http://localhost:8080/stream/create-employee-data")
            .retrieve()
            .bodyToFlux(Employee.class);
            //.subscribe(System.out::println); //.subscribe(t->{System.out.println(t);});

        employeeStream.subscribe(employee-> {kafkaProducerService.publish("streaming_data", employee);});

    }

    @GetMapping(value  = "/stream/read-and-publish/with-delay", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public void streamDataWithDelay(){

            WebClient.create()
                .get().uri("http://localhost:8080/kafka/stream/create-employee-data")
                .retrieve()
                .bodyToFlux(Employee.class)
                .delaySubscription(Duration.ofSeconds(5)) // Start subscribing after 5 sec
                .subscribe(employee -> kafkaProducerService.publish("streaming_data", employee));

    }
}
