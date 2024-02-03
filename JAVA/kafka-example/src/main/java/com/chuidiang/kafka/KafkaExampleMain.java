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
import org.apache.kafka.common.config.TopicConfig;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author fjabellan 24/01/2024
 */
public class KafkaExampleMain {

    public static final String KAFKA_URL = "localhost:29092";
    public static final String TOPIC = "quickstart-events";

    public static void main(String[] args) {
        createTopic();
        consumer("1");
        consumer("2");
        producer();
    }

    private static void producer() {
        new Thread(()->{
            Properties props = new Properties();
            props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL);
            props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
            props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

            Producer<String, String> producer = new KafkaProducer<>(props);
            for (int i = 0; i < 100; i++) {
                producer.send(new ProducerRecord<String, String>(TOPIC, Integer.toString(i), Integer.toString(i)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            producer.close();
        }).start();
    }

    private static void consumer(String id) {
        new Thread(()->{
            Properties props = new Properties();
            props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL);
            props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test3");
            props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
            props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
            props.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
            props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList(TOPIC));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records)
                    System.out.printf("%s: offset = %d, key = %s, value = %s%n", id, record.offset(), record.key(), record.value());
            }
        }).start();
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
                    new NewTopic(topicName, partitions, replicationFactor)
                            .configs(Collections.singletonMap(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT))));

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
