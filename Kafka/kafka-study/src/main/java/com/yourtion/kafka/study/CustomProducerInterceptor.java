package com.yourtion.kafka.study;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yourtion
 */
public class CustomProducerInterceptor implements ProducerInterceptor<String, String> {
    public static final String PREFIX = "y-";
    private AtomicInteger sendSuccess = new AtomicInteger();
    private AtomicInteger sendFailure = new AtomicInteger();

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> r) {
        String modifiedValue = PREFIX + r.value();
        return new ProducerRecord<>(r.topic(), r.partition(), r.timestamp(), r.key(), modifiedValue, r.headers());
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e == null) {
            sendSuccess.incrementAndGet();
        } else {
            sendFailure.incrementAndGet();
        }
    }

    @Override
    public void close() {
        double successRatio = (double) sendSuccess.intValue() / (sendFailure.intValue() + sendSuccess.intValue());
        System.out.printf("[INFO] 发送成功率 = %f %%\n", successRatio * 100);
    }

    @Override
    public void configure(Map<String, ?> map) {
    }
}
