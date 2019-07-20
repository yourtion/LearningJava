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

    private static void generalConsumeMessageAsyncCommit() {
        properties.put("enable.auto.commit", false);
        consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singleton("yourtion-kafka-study-x"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            boolean flag = processRecords(records);
            try {
                // 无法重试或者处理提交失败
                consumer.commitAsync();
            } catch (CommitFailedException e) {
                System.out.println("commit failed error: " + e.getMessage());
            }
            if (!flag) {
                break;
            }
        }
        consumer.close();
    }

    public static void main(String[] args) {
        generalConsumeMessageAsyncCommit();
    }
}
