package com.engrkirky.service_metrics_service.consumer;

import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.engrkirky.service_metrics_service.config.KafkaTopicConfig.SPEED_TEST_TOPIC;
import static com.engrkirky.service_metrics_service.util.KafkaConstants.SERVICE_METRICS_PIPELINE_GROUP_ID;

@Component
public class SpeedTestConsumer {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SpeedTestConsumer.class);

    @KafkaListener(topics = SPEED_TEST_TOPIC, groupId = SERVICE_METRICS_PIPELINE_GROUP_ID )
    public void listen(String message) {
        log.info("Received message: {}", message);
    }
}
