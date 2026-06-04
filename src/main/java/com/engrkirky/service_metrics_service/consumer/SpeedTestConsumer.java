package com.engrkirky.service_metrics_service.consumer;

import com.engrkirky.service_metrics_service.dto.SpeedTestDTO;
import com.engrkirky.service_metrics_service.service.SpeedTestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.engrkirky.service_metrics_service.config.KafkaTopicConfig.SPEED_TEST_TOPIC;
import static com.engrkirky.service_metrics_service.util.KafkaConstants.SERVICE_METRICS_PIPELINE_GROUP_ID;

/**
 * Kafka consumer for processing speed test messages.
 */
@Component
public class SpeedTestConsumer {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SpeedTestConsumer.class);
    private final SpeedTestService speedTestService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public SpeedTestConsumer(SpeedTestService speedTestService) {
        this.speedTestService = speedTestService;
    }

    /**
     * Consumes and processes speed test messages from Kafka.
     *
     * @param message JSON message containing speed test data
     */
    @KafkaListener(topics = SPEED_TEST_TOPIC, groupId = SERVICE_METRICS_PIPELINE_GROUP_ID)
    public void listen(String message) {
        try {
            log.info("Received message: {}", message);
            SpeedTestDTO speedTestDTO = objectMapper.readValue(message, SpeedTestDTO.class);
            speedTestService.addSpeedTest(speedTestDTO);
        } catch (Exception e) {
            log.error("Error while parsing message: {}", message, e);
        }
    }
}
