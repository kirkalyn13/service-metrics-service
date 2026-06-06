package com.engrkirky.service_metrics_service.service;

import com.engrkirky.service_metrics_service.dto.SpeedTestDTO;
import com.engrkirky.service_metrics_service.mapper.SpeedTestMapper;
import com.engrkirky.service_metrics_service.model.SpeedTest;
import com.engrkirky.service_metrics_service.repository.SpeedTestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpeedTestServiceImplTest {

    @Mock
    private SpeedTestRepository speedTestRepository;

    @Mock
    private SpeedTestMapper speedTestMapper;

    @InjectMocks
    private SpeedTestServiceImpl underTest;

    private SpeedTestDTO dto;
    private SpeedTest entity;

    @BeforeEach
    void setUp() {
        dto = new SpeedTestDTO(
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

        entity = SpeedTest.builder()
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
    }

    @Test
    void addsSpeedTest() {
        when(speedTestMapper.convertToEntity(dto)).thenReturn(entity);

        underTest.addSpeedTest(dto);

        verify(speedTestMapper).convertToEntity(dto);
        verify(speedTestRepository).save(entity);
    }

    @Test
    void throwsRuntimeExceptionOnFailure() {
        when(speedTestMapper.convertToEntity(dto)).thenThrow(new RuntimeException("Mapper error"));

        assertThrows(RuntimeException.class, () -> underTest.addSpeedTest(dto));

        verify(speedTestRepository, never()).save(any());
    }
}
