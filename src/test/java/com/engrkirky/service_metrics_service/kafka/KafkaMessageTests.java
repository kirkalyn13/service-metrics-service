package com.engrkirky.service_metrics_service.kafka;

import com.engrkirky.service_metrics_service.dto.SpeedTestResultDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
import java.util.List;

import static com.engrkirky.service_metrics_service.config.KafkaTopicConfig.SPEED_TEST_TOPIC;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@EmbeddedKafka(topics = SPEED_TEST_TOPIC, partitions = 1)
public class KafkaMessageTests {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void sendsAndReceivesMessage() throws Exception {
        var consumerProps = KafkaTestUtils.consumerProps(embeddedKafka, "test-group", true);
        try (Consumer<String, String> consumer = new KafkaConsumer<>(consumerProps, new StringDeserializer(), new StringDeserializer())) {

            consumer.subscribe(List.of(SPEED_TEST_TOPIC));
            KafkaTestUtils.getRecords(consumer, Duration.ofSeconds(1));

            SpeedTestResultDTO testData = new SpeedTestResultDTO(
                    "2026-05-25T09:24:36.148779Z",
                    "Spectrum", "192.168.0.10", "Nashville, TN",
                    1037.11, 39.28, 28.0, 34.0, 27.0
            );

            String json = objectMapper.writeValueAsString(testData);
            kafkaTemplate.send(SPEED_TEST_TOPIC, json);

            ConsumerRecord<String, String> record = KafkaTestUtils.getSingleRecord(consumer, SPEED_TEST_TOPIC, Duration.ofSeconds(5));

            assertThat(record.value()).isEqualTo(json);
        }
    }
}
