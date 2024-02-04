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
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author fjabellan 24/01/2024
 */
public class KafkaComplexExampleMain {

    public static final String KAFKA_URL = "localhost:29092";
    public static final String TOPIC = "quickstart-events";

    public static void main(String[] args) {
        createTopic();
        consumer("Consumer Group 1", 1);
        consumer("Consumer Group 1", 2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer();
    }

    private static void producer() {
        new Thread(()->{
            Properties props = new Properties();
            props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL);
            props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

            try(Producer<String, String> producer = new KafkaProducer<>(props)) {
                for (int i = 0; i < 100; i++) {
                    String key = Integer.toString(i%2);
                    String message = Integer.toString(i);
                    producer.send(new ProducerRecord<String, String>(TOPIC, key, message));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }

            System.out.println("Productor terminado");
        }).start();
    }

    private static void consumer(String consumerName, int consumerId) {
        new Thread(()->{
            Properties props = new Properties();
            props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL);
            props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerName);
            props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
            props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
            props.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
            props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
                consumer.subscribe(Arrays.asList(TOPIC));
                while (true) {
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records)
                        System.out.printf("%s: offset = %d, key = %s, value = %s%n", consumerId, record.offset(), record.key(), record.value());
                }
            }
        }).start();
    }

    private static void createTopic(){
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL);

        try (Admin admin = Admin.create(props)) {
            String topicName = TOPIC;
            int partitions = 2;
            short replicationFactor = 1;

            ListTopicsResult listTopicsResult = admin.listTopics();
            KafkaFuture<Set<String>> names = listTopicsResult.names();
            Set<String> strings = names.get();
            strings.forEach(System.out::println);

            // Se borra el topic si existe, para recrearlo desde cero.
            if (strings.contains(TOPIC)){
                final DeleteTopicsResult deleteTopicsResult = admin.deleteTopics(Collections.singleton(TOPIC));
                deleteTopicsResult.all().get();
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
