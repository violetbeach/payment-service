package com.violetbeach.taskconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TaskResultProducer {
    private final KafkaProducer<String, String> producer;
    private final String topic;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TaskResultProducer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
        @Value("${task.result.topic}")String topic) {

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    public void sendTaskResult(String key, Object task) {
        String jsonStringToProduce;
        try {
            jsonStringToProduce = objectMapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonStringToProduce);
        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                log.error("Failed to send message.", exception);
            }
        });
    }
}