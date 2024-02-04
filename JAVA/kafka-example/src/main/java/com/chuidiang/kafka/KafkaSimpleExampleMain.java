package com.chuidiang.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaFuture;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @author fjabellan 24/01/2024
 */
public class KafkaSimpleExampleMain {

    public static final String KAFKA_URL = "localhost:29092";
    public static final String TOPIC = "quickstart-events";

    public static void main(String[] args) {
        createTopic();
        consumer();
        producer();
    }

    private static void producer() {
        new Thread(()->{
            Properties props = new Properties();
            props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL);
            props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

            int i=0;
            try(Producer<String, String> producer = new KafkaProducer<>(props)) {
                while(i<100){
                    producer.send(new ProducerRecord<String, String>(TOPIC, Integer.toString(i)));
                    i++;
                    Thread.sleep(100);
                    System.out.println("Producer envia: "+i);
                }
                System.out.println("Termina el Producer");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void consumer() {
        Thread consumerThread = new Thread(() -> {
            Properties props = new Properties();
            props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL);
            props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
            props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
                consumer.subscribe(Collections.singleton(TOPIC));
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));
                    if (records.isEmpty()){
                        break;
                    }
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.printf("value = %s\n", record.value());
                    }
                }
                System.out.println("Termina el Cosumer");
            }
        });
        consumerThread.start();
    }

    private static void createTopic(){
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL);

        try (Admin admin = Admin.create(props)) {
            String topicName = TOPIC;
            int partitions = 12;
            short replicationFactor = 1;

            ListTopicsResult listTopicsResult = admin.listTopics();
            KafkaFuture<Set<String>> names = listTopicsResult.names();
            Set<String> strings = names.get();
            strings.forEach(System.out::println);
            if (strings.contains(TOPIC)){
                return;
            }

            // Create a compacted topic
            CreateTopicsResult result = admin.createTopics(Collections.singleton(
                    new NewTopic(topicName, partitions, replicationFactor)));

            // Call values() to get the result for a specific topic
            KafkaFuture<Void> future = result.values().get(topicName);

            // Call get() to block until the topic creation is complete or has failed
            // if creation failed the ExecutionException wraps the underlying cause.
            future.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
