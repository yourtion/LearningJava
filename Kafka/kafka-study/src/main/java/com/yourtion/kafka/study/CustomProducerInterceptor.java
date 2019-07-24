package com.yourtion.kafka.study;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义生产者拦截器
 * 给消息体内容加上 y- 前缀，统计发送成功率
 * @author yourtion
 */
public class CustomProducerInterceptor implements ProducerInterceptor<String, String> {
    private static final String PREFIX = "y-";
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
