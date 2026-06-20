package com.engrkirky.service_metrics_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

/**
 * Kafka error handling configuration with retry and dead-letter publishing.
 */
@Configuration
public class KafkaErrorConfig {
    private static final long INTERVAL = 1000L;
    private static final long MAX_ATTEMPTS = 1000L;

    /**
     * Configures a Kafka error handler with retry and DLQ support.
     *
     * @param kafkaTemplate Kafka template used for dead-letter publishing
     * @return configured error handler
     */
    @Bean
    public DefaultErrorHandler errorHandler(KafkaTemplate<String, Object> kafkaTemplate) {
        DeadLetterPublishingRecoverer recoverer =
                new DeadLetterPublishingRecoverer(kafkaTemplate);

        return new DefaultErrorHandler(recoverer, new FixedBackOff(INTERVAL, MAX_ATTEMPTS));
    }
}
