package com.engrkirky.service_metrics_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a recorded internet speed test result.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SpeedTestResult (
    String timestamp,
    String isp,
    String ip,
    String location,
    double downloadSpeedMbps,
    double uploadSpeedMbps,
    double idleLatencyMs,
    double downloadLatencyMs,
    double uploadLatencyMs
) {}
