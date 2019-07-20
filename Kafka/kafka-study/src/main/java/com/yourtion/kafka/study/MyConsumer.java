package com.yourtion.kafka.study;

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

    private static void generalConsumeMessageAutoCommit() {
        properties.put("enable.auto.commit", true);
        consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singleton("yourtion-kafka-study-x"));

        try {
            while (true) {

                boolean flag = true;
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(String.format(
                            "topic = %s, partition = %s, key = %s, value = %s",
                            record.topic(), record.partition(), record.key(), record.value())
                    );
                    if (record.value().equals(DONE_TAG)) {
                        flag = false;
                    }
                }
                if (!flag) {
                    break;
                }
            }
        } finally {
            consumer.close();
        }
    }

    public static void main(String[] args) {
        generalConsumeMessageAutoCommit();
    }
}
