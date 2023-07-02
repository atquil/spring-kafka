# Apache Kafka using spring boot

### Best Documentation : 
  
  1. [Spring Boot with Apache Kafka](https://docs.spring.io/spring-kafka/docs/current/reference/html/#preface)
  2. [Apache Kafka Official ](https://kafka.apache.org/documentation/)

<br>

## History
Traditional messaging queue like ActiveMQ and RabitMQ were made to handle high throughput with long-running background jobs. 

Kafka is a stream-processing platform built by LinkedIn and currently developed under the umbrella of the Apache Software Foundation. Kafka aims to provide low-latency ingestion of large amounts of event data.
Apache. 

### `Kafka is a distributed and fault-tolerant stream processing system`

<br>

### Kafka terms:

1. ***Message***: Data that we need to send will be divided into `set of messages[Array of bytes]`. For example, for a csv file [data], each line can be a message to be send. 
2. ***Topic***: `category name` to which message are published and from which consumers receive messages. 
3. ***Producer***: Client that `send message` to server using specific `topic` to `Kafka server`
4. ***Consumer***: Recipient that `receive message` from `Kafka server` by `subscribing` to specific `topic`. 
5. ***Broker***: Agent between producer and consumer
6. ***Cluster***: They are the group of system i.e. `Brokers` having distributed workload to be efficient in high-workload [Min 3 broker to become cluster]
7. ***Partition***: A topic, which has a higher volume data, can be partitioned into multiple parts and distributed among multiple computers. 
8. ***Offsets***: As `data` (csv) is divided into `several messages` , to be sent in different `partition` , it needs to have some `ids` for knowing the `sequence of message` this is called `offsets`. Offset are `local to a partition` and will never change. 
9. ***Zookeeper***: It's the brain, which `manages kafka cluster , stores offsets for all the partitions of all the topics` and also maintain the `client quotas` i.e. how much data a producer/consumer is allowed to
10. **Schema**: Messaging systems can use schemas to define the structure of messages. Schemas can help ensure that messages are well-formed and can be easily processed by different applications or services.

### Different Messaging Pattern in Kafka: 
1. **Command Model**: In the command model, `messages are sent to a single consumer` that is responsible for processing the message. The consumer can then send a response back to the producer.
2. **Choreography**: In choreography, `messages are sent between different services to coordinate their actions`. Each service is responsible for performing its own actions based on the messages it receives.
3. **Event-Driven Architecture (EDA)**: In EDA, `messages are sent between different services to notify them of events that have occurred.` Each service can then perform its own actions based on the events it receives.


### Use Case: 
Kafka is used for building real-time data pipelines and streaming applications. Here are some use cases for Kafka:

1. **Log Aggregation**: Kafka can be used to collect logs from different applications and services and store them in a centralized location for analysis.
2. **Stream Processing**: Kafka can be used to process streams of data in real-time. This can be useful for applications such as fraud detection, real-time analytics, and monitoring.
3. **Event Sourcing**: Kafka can be used to implement event sourcing, which is a way of storing data as a series of events. This can be useful for applications such as financial systems and e-commerce platforms.
4. **Messaging**: Kafka can be used as a messaging system to send messages between different applications or services.
5. **Metrics**: Kafka can be used to collect metrics from different applications and services and store them in a centralized location for analysis.

## Steps for local : https://kafka.apache.org/quickstart 


1. Download the latest kafka (recommended)
2. Unzip folder , rename the folder to `kafka` and go to particular folder
   ```
   $tar -xzf kafka<Your downloaded version>.tgz
   
   $ cd kafka
   ```
3. Start `zookeeper service ` : 
   
   Note: Soon, ZooKeeper will no longer be required by Apache Kafka.

   ```
   $ bin/zookeeper-server-start.sh config/zookeeper.properties
   ```
4. Start `broker service` in ***Other terminal***

   ```
   $ bin/kafka-server-start.sh config/server.properties
   ```

5. To `close` servers at any point just close the terminal


### Kafka Dependency 

gradle

```
 implementation 'org.springframework.kafka:spring-kafka'
```

maven
```
    <dependency>
      <groupId>org.springframework.kafka</groupId>
      <artifactId>spring-kafka</artifactId>
    </dependency>

```
### Other imp commands

1. Read the kafka topics from terminal 

```
$ bin/kafka-console-consumer.sh --topic <Your Topic Name> --from-beginning --bootstrap-server localhost:9092
```

2. Post API

```
http://localhost:8080/send

Body : 
{
    "name":"Something",
    "id":"BCS2"
}

```