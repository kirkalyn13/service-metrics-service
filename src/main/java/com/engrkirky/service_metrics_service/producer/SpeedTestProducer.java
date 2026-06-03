package com.engrkirky.service_metrics_service.producer;

import com.engrkirky.service_metrics_service.dto.SpeedTestResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.engrkirky.service_metrics_service.config.KafkaTopicConfig.SPEED_TEST_TOPIC;

@Service
public class SpeedTestProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public SpeedTestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(SpeedTestResult result) throws JsonProcessingException {
        kafkaTemplate.send(SPEED_TEST_TOPIC, objectMapper.writeValueAsString(result));
    }
}
