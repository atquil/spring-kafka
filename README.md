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
3. Start `broker service` in ***Other terminal***

```
$ bin/kafka-server-start.sh config/server.properties
```

4. To `close` servers at any point just close the terminal


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
