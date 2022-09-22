package com.atquil.springkafka.configuration.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class KafkaConsumerConfig {
    
     //Steps for producer

    // Step 1 : Where to send the topics i.e. port or ip

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;// = "localhost:9092";

    // Step 2: Configure the Producer either in Spring or in application.properties/application.yml file

    //! There is one more way to set this config .. directly in the application.yml file 
   
    @Bean
    public Map<String, Object> consumerConfig(){
        
        // Now we want to set the properties for the Map object we want to send
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
        
        
    }

    // Step 3: Add those config and create a producer object , with the data you want to send.  
    // e.g. ProducerFactory<String, Employee>
    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    // Step 4: Now send the message using KafkaTemplate
    @Bean
    public KafkaListenerContainerFactory<
        ConcurrentMessageListenerContainer<String, String>> factory(ConsumerFactory<String, String> consumerFactory) {
            ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory);
            return factory;
        }
}
