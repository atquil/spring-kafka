package com.atquil.springkafka.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.atquil.springkafka.entity.Employee;
import com.atquil.springkafka.service.KafkaProducerService;

import lombok.RequiredArgsConstructor;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class StreamAPIUsingWebFlux {
/*
    Spring WebFlux: Spring WebFlux supports fully non blocking reactive streams

 */

 private final KafkaProducerService kafkaProducerService;
// ************** Creating stream API working ********************* 

@GetMapping(value = "/stream/data" , produces = MediaType.APPLICATION_NDJSON_VALUE)
public Flux<Object> streamDataFlux() {
    
  return Flux.interval(Duration.ofSeconds(1)).map(i -> "Data stream line - " + i );
}
// Reading the stream data
    

@GetMapping(value = "/json/data" , produces = MediaType.APPLICATION_NDJSON_VALUE)
public Flux<Employee> streamJsonObjects() {
  return Flux.interval(Duration.ofSeconds(1)).map(i -> new Employee("Name" + i, i.toString()));
}

// ************** Reading stream API working ********************* 

@GetMapping(value  = "/stream/read", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public void readStreamDataFromEndpoint(){

        
     Flux<Employee> x =    WebClient.create()
            .get().uri("http://localhost:8080/json/data")
           // .accept(MediaType.APPLICATION_JSON)

            .retrieve()
            
          .bodyToFlux(Employee.class);
            
           // .doOnSubscribe((t) ->{System.out.println(t);} )
         //   .log();
            //.delaySubscription(Duration.ofSeconds(5))
          //  .doOnSubscribe(population->{System.out.println(population);})
// .repeat();
          //  kafkaProducerService.sendMessage("Afd","Asdf")
        x.subscribe(t->{System.out.println(t);});
    }

    @GetMapping(value  = "/stream/read1", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public void adfs(){

        
        Disposable x =    WebClient.create()
        .get().uri("http://localhost:8080/json/data")
       // .accept(MediaType.APPLICATION_JSON)

        .retrieve()
        
      .bodyToFlux(Employee.class)
      .delaySubscription(Duration.ofSeconds(2))
      .subscribe(t->{System.out.println(t);});
        
       // .doOnSubscribe((t) ->{System.out.println(t);} )
     //   .log();
        //.delaySubscription(Duration.ofSeconds(5))
      //  .doOnSubscribe(population->{System.out.println(population);})
// .repeat();
      //  kafkaProducerService.sendMessage("Afd","Asdf")
  //  x.subscribe(t->{System.out.println(t);});
       
    }
    
/// But if you have configured the database, then you can mention the repository’s method directly which will return a list of objects.
// Flux automatically manages the database cursors, 
//so for a large dataset, it streams all the database objects without overloading the server resources.
/* 
@GetMapping(value = "/json/flux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
public Flux<PopulationList> streamJsonObjectsFromDb() {
  return studentRepository.findAll();
}
*/
}
