package com.engrkirky.service_metrics_service.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.engrkirky.service_metrics_service.config.KafkaTopicConfig.SPEED_TEST_TOPIC;

@Service
public class SpeedTestProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public SpeedTestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String message) {
        kafkaTemplate.send(SPEED_TEST_TOPIC, message);;
    }
}
