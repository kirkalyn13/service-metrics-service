package com.engrkirky.service_metrics_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    public static final String SPEED_TEST_TOPIC = "speed_test";

    @Bean
    NewTopic speedTestTopic() {
        return TopicBuilder.name(SPEED_TEST_TOPIC).partitions(1).replicas(1).build();
    }
}
