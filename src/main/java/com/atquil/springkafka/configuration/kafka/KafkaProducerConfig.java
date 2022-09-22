package com.atquil.springkafka.configuration.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {

    //Steps for producer

    // Step 1 : Where to send the topics i.e. port or ip
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;


    // Step 2: Configure the Producer either in Spring or in application.properties/application.yml file

    //! There is one more way to set this config .. directly in the application.yml file 
   
    @Bean
    public Map<String, Object> produceConfig(){
       
        // Now we want to set the properties for the Map object we want to send
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
        
        
    }

    // Step 3: Add those config and create a producer object , with the data you want to send.  
    // e.g. ProducerFactory<String, Employee>
    @Bean
    public ProducerFactory<String, String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(produceConfig());
    }

    // Step 4: Now send the message using KafkaTemplate
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String,String> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
    
}
