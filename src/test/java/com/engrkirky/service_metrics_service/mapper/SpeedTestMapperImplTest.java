package com.engrkirky.service_metrics_service.mapper;

import com.engrkirky.service_metrics_service.dto.SpeedTestDTO;
import com.engrkirky.service_metrics_service.model.SpeedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpeedTestMapperImplTest {
    private SpeedTestMapperImpl underTest;

    @BeforeEach
    public void setUp() {
        underTest = new SpeedTestMapperImpl();
    }

    SpeedTestDTO dto = new SpeedTestDTO(
            "2026-05-25T09:24:36.148779Z",
            "Spectrum",
            "192.168.0.1",
            "Nashville, TN",
            1037.11,
            39.28,
            28.0,
            34.0,
            27.0
    );

    SpeedTest entity = SpeedTest.builder()
            .timestamp("2026-05-25T09:24:36.148779Z")
            .isp("Spectrum")
            .ip("192.168.0.1")
            .location("Nashville, TN")
            .downloadSpeedMbps(1037.11)
            .uploadSpeedMbps(39.28)
            .idleLatencyMs(28.0)
            .downloadLatencyMs(34.0)
            .uploadLatencyMs(27.0)
            .build();

    @Test
    void canConvertToDTO() {
        SpeedTestDTO result = underTest.convertToDTO(entity);
        assertEquals(result, dto);
    }

    @Test
    void canConvertToEntity() {
        SpeedTest result = underTest.convertToEntity(dto);
        assertEquals(result, entity);
    }
}
