package com.yourtion.kafka.study;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author yourtion
 */
public class MyConsumer {

    private static final String DONE_TAG = "done";
    private static KafkaConsumer<String, String> consumer;
    private static Properties properties = new Properties();

    static {
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "KafkaStudy");

    }

    private static boolean processRecords(ConsumerRecords<String, String> records) {
        boolean flag = true;
        for (ConsumerRecord<String, String> record : records) {
            System.out.println(String.format(
                    "topic = %s, partition = %s, key = %s, value = %s",
                    record.topic(), record.partition(), record.key(), record.value())
            );
            if (record.value().equals(DONE_TAG)) {
                flag = false;
            }
        }
        return flag;
    }

    private static void generalConsumeMessageAutoCommit() {
        properties.put("enable.auto.commit", true);
        consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singleton("yourtion-kafka-study-x"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                boolean flag = processRecords(records);
                if (!flag) {
                    break;
                }
            }
        } finally {
            consumer.close();
        }
    }

    private static void generalConsumeMessageSyncCommit() {
        properties.put("enable.auto.commit", false);
        consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singleton("yourtion-kafka-study-x"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            boolean flag = processRecords(records);
            try {
                consumer.commitSync();
            } catch (CommitFailedException e) {
                System.out.println("commit failed error: " + e.getMessage());
            }
            if (!flag) {
                break;
            }
        }
        consumer.close();
    }

    private static void generalConsumeMessageAsyncCommitWithCallback() {
        properties.put("enable.auto.commit", false);
        consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singleton("yourtion-kafka-study-x"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            boolean flag = processRecords(records);

            consumer.commitAsync((map, e) -> {
                if (e != null) {
                    System.out.println("commit failed for offset: " + e.getMessage());
                }
            });

            if (!flag) {
                break;
            }
        }
        consumer.close();
    }

    private static void mixSyncAndAsyncCommit() {
        properties.put("enable.auto.commit", false);
        consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singleton("yourtion-kafka-study-x"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                boolean flag = processRecords(records);
                consumer.commitAsync();
                if (!flag) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("commit failed error: " + e.getMessage());
        } finally {
            // 捕获异步提交异常，尝试同步提交保证提交结果
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }

    public static void main(String[] args) {
        mixSyncAndAsyncCommit();
    }
}
