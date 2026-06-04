package com.engrkirky.service_metrics_service.dto;

public record KafkaResponse (
    String status,
    String topic
) {}
