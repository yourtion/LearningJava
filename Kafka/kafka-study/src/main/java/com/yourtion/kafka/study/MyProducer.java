package com.yourtion.kafka.study;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author yourtion
 */
public class MyProducer {

    private static KafkaProducer<String, String> producer;

    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(properties);
    }
    private static void sendMessageForgetResult() {

        ProducerRecord<String, String> record = new ProducerRecord<>(
                "yourtion-kafka-study",
                "name",
                "ForgetResult"
        );
        producer.send(record);
        producer.close();
    }

    public static void main(String[] args) {

        // kafka-console-consumer --bootstrap-server localhost:9092 --topic yourtion-kafka-study --from-beginning
        sendMessageForgetResult();
    }
}
