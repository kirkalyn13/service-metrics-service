package com.engrkirky.service_metrics_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a recorded internet speed test result.
 */
public record SpeedTestResult (
    String timestamp,
    String isp,
    String ip,
    String location,
    @JsonProperty("download_speed_mbps") double downloadSpeedMbps,
    @JsonProperty("upload_speed_mbps") double uploadSpeedMbps,
    @JsonProperty("idle_latency_ms") double idleLatencyMs,
    @JsonProperty("download_latency_ms") double downloadLatencyMs,
    @JsonProperty("upload_latency_ms") double uploadLatencyMs
) {}
