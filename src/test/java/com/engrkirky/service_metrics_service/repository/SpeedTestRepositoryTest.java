package com.engrkirky.service_metrics_service.repository;

import com.engrkirky.service_metrics_service.model.SpeedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class SpeedTestRepositoryTest {

    @Autowired
    private SpeedTestRepository speedTestRepository;

    @Test
    void savesSpeedTest() {
        SpeedTest entity = SpeedTest.builder()
                .isp("Spectrum")
                .ip("192.168.0.1")
                .location("Nashville, TN")
                .downloadSpeedMbps(1037.11)
                .uploadSpeedMbps(39.28)
                .idleLatencyMs(28.0)
                .downloadLatencyMs(34.0)
                .uploadLatencyMs(27.0)
                .build();

        SpeedTest saved = speedTestRepository.save(entity);

        assertThat(saved.getId()).isNotNull();
        assertThat(speedTestRepository.findById(saved.getId())).isPresent();
    }
}