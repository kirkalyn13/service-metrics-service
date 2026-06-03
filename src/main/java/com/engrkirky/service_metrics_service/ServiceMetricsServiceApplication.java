package com.engrkirky.service_metrics_service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class ServiceMetricsServiceApplication {
	private static final String KAFKA_TOPIC = "speed_test";

	public static void main(String[] args) {
		SpringApplication.run(ServiceMetricsServiceApplication.class, args);
	}

	@Bean
	NewTopic speedTestTopic() {
		return TopicBuilder.name(KAFKA_TOPIC).partitions(1).replicas(1).build();
	}

	@Bean
	ApplicationRunner runner(KafkaTemplate<String, String> template) {
		return args -> {template.send(KAFKA_TOPIC, "Speed Test Data.");};
	}

	@KafkaListener(topics = KAFKA_TOPIC, groupId = "service_metrics_pipeline")
	public void listen(String message) {
		System.out.println("Received message: " + message);
	}

}
