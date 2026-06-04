package com.engrkirky.service_metrics_service.producer;

import com.engrkirky.service_metrics_service.dto.KafkaResponse;
import com.engrkirky.service_metrics_service.dto.SpeedTestResult;
import com.engrkirky.service_metrics_service.util.KafkaConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.engrkirky.service_metrics_service.config.KafkaTopicConfig.SPEED_TEST_TOPIC;

@Service
public class SpeedTestProducer {
    private static final Logger log = LoggerFactory.getLogger(SpeedTestProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public SpeedTestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(SpeedTestResult result) {
        try {
            String message = objectMapper.writeValueAsString(result);
            kafkaTemplate.send(SPEED_TEST_TOPIC, message);
            log.info("Published speed test result: {}", message);
        } catch (Exception e) {
            log.error("Error publishing speed test result", e);
            throw new RuntimeException(e);
        }
    }
}
