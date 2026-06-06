package com.engrkirky.service_metrics_service.producer;

import com.engrkirky.service_metrics_service.dto.SpeedTestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static com.engrkirky.service_metrics_service.config.KafkaTopicConfig.SPEED_TEST_TOPIC;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpeedTestProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private SpeedTestProducer speedTestProducer;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private SpeedTestDTO dto;

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
    }

    @Test
    void publishesSpeedTestToKafka() throws Exception {
        String expectedJson = objectMapper.writeValueAsString(dto);

        speedTestProducer.publish(dto);

        verify(kafkaTemplate).send(SPEED_TEST_TOPIC, expectedJson);
    }

    @Test
    void throwsRuntimeExceptionOnFailure() {
        when(kafkaTemplate.send(any(), any())).thenThrow(new RuntimeException("Kafka error"));

        assertThrows(RuntimeException.class, () -> speedTestProducer.publish(dto));
    }
}
