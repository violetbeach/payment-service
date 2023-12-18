package com.violetbeach.money.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.violetbeach.common.task.RechargingMoneyTask;
import com.violetbeach.money.application.port.out.SendRechargingMoneyTaskPort;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class TaskProducer implements SendRechargingMoneyTaskPort {

    private final KafkaProducer<String, String> producer;
    private final String topic;
    private final ObjectMapper mapper = new ObjectMapper();

    public TaskProducer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
        @Value("${task.topic}") String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    @Override
    public void sendRechargingMoneyTaskPort(RechargingMoneyTask task) {
        this.sendMessage(task.getTaskID(), task);
    }

    public void sendMessage(String key, RechargingMoneyTask value) {
        String jsonStringToProduce;
        // jsonString
        try {
            jsonStringToProduce = mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonStringToProduce);
        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                log.error("Failed to send message. ", exception);
            }
        });
    }
}