package com.engrkirky.service_metrics_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="raw_speed_test")
public class SpeedTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "isp")
    private String isp;

    @Column(name = "ip")
    private String ip;

    @Column(name = "location")
    private String location;

    @Column(name = "download_speed_mbps")
    private double downloadSpeedMbps;

    @Column(name = "upload_speed_mbps")
    private double uploadSpeedMbps;

    @Column(name = "idle_latency_ms")
    private double idleLatencyMs;

    @Column(name = "download_latency_ms")
    private double downloadLatencyMs;

    @Column(name = "upload_latency_ms")
    private double uploadLatencyMs;
}
