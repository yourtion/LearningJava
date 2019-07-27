package com.yourtion.kafka.study;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author yourtion
 */
public class MyProducer {

    private static KafkaProducer<String, String> producer;
    private static final long EXPIRE_INTERVAL = 10 * 1000;

    static {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, CustomProducerInterceptor.class.getName());

        producer = new KafkaProducer<>(properties);
    }

    private static void sendMessageForgetResult() {

        ProducerRecord<String, String> record = new ProducerRecord<>(
                "yourtion-kafka-study", 0, System.currentTimeMillis() - EXPIRE_INTERVAL, "name", "first-expire-data"
        );
        producer.send(record);
        record = new ProducerRecord<>(
                "yourtion-kafka-study", "name", "normal-data"
        );
        producer.send(record);
        record = new ProducerRecord<>(
                "yourtion-kafka-study", 0, System.currentTimeMillis() - EXPIRE_INTERVAL, "name", "last-expire-data"
        );
        producer.send(record);
        producer.close();
    }

    private static void sendMessageSync() throws Exception {

        ProducerRecord<String, String> record = new ProducerRecord<>(
                "yourtion-kafka-study", "name", "sendMessageSync"
        );
        RecordMetadata result = producer.send(record).get();
        System.out.println(result.topic());
        System.out.println(result.partition());
        System.out.println(result.offset());

        producer.close();
    }

    private static void sendMessageCallback() {

        ProducerRecord<String, String> record = new ProducerRecord<>(
                "yourtion-kafka-study", "name", "sendMessageCallback"
        );
        producer.send(record, new MyProducerCallback());

        producer.close();
    }

    private static void sendMessage(String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(
                "yourtion-kafka-study-x", key, value
        );
        producer.send(record, new MyProducerCallback());
    }

    public static void main(String[] args) throws Exception {

        // 命令行消费：kafka-console-consumer --bootstrap-server localhost:9092 --topic yourtion-kafka-study --from-beginning
        // 创建topic：kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic yourtion-kafka-study-x

//        sendMessageForgetResult();
//        sendMessageSync();
        sendMessage("name", "sendMessage-name");
        sendMessage("name-x", "sendMessage-name-x");
        sendMessage("name-y", "sendMessage-name-y");
        sendMessage("name-z", "sendMessage-name-z");
        // null key error
        try {
            sendMessage(null, "sendMessage-name-z");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sendMessage("name-z", "done");
        sendMessageCallback();
    }

    private static class MyProducerCallback implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {

            if (e != null) {
                e.printStackTrace();
                return;
            }

            System.out.println("Coming in MyProducerCallback");
            System.out.println(recordMetadata.topic());
            System.out.println(recordMetadata.partition());
            System.out.println(recordMetadata.offset());

            System.out.println();
        }
    }
}
