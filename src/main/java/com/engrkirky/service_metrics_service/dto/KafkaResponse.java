package com.engrkirky.service_metrics_service.dto;

/**
 * Response returned for Kafka publish operations.
 */
public record KafkaResponse (
    String status,
    String topic
) {}
