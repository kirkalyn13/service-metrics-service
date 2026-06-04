package com.engrkirky.service_metrics_service.producer;

import com.engrkirky.service_metrics_service.dto.SpeedTestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.engrkirky.service_metrics_service.config.KafkaTopicConfig.SPEED_TEST_TOPIC;

/**
 * Kafka producer for publishing speed test messages.
 */
@Service
public class SpeedTestProducer {
    private static final Logger log = LoggerFactory.getLogger(SpeedTestProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public SpeedTestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publishes a speed test message to Kafka.
     *
     * @param speedTestDTO speed test data to publish
     */
    public void publish(SpeedTestDTO speedTestDTO) {
        try {
            String message = objectMapper.writeValueAsString(speedTestDTO);
            kafkaTemplate.send(SPEED_TEST_TOPIC, message);
            log.info("Published speed test result: {}", message);
        } catch (Exception e) {
            log.error("Error publishing speed test result", e);
            throw new RuntimeException(e);
        }
    }
}
