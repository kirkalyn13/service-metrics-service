package com.engrkirky.service_metrics_service.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@EmbeddedKafka(topics = "speed_test", partitions = 1)
public class KafkaMessageTests {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;

    @Test
    void sendsAndReceivesMessage() {
        var consumerProps = KafkaTestUtils.consumerProps(embeddedKafka, "test-group", true);
        try (Consumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(consumerProps, new StringDeserializer(), new StringDeserializer())) {

            consumer.subscribe(java.util.List.of("speed_test"));

            // Drain anything the app already produced on startup
            KafkaTestUtils.getRecords(consumer, Duration.ofSeconds(1));
            kafkaTemplate.send("speed_test", "hello from the test");
            ConsumerRecord<String, String> record = KafkaTestUtils.getSingleRecord(consumer, "speed_test", Duration.ofSeconds(5));

            assertThat(record.value()).isEqualTo("hello from the test");
        }
    }
}
