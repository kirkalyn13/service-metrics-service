package com.engrkirky.service_metrics_service.controller;

import com.engrkirky.service_metrics_service.dto.KafkaResponse;
import com.engrkirky.service_metrics_service.dto.SpeedTestResult;
import com.engrkirky.service_metrics_service.producer.SpeedTestProducer;
import com.engrkirky.service_metrics_service.util.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.engrkirky.service_metrics_service.config.KafkaTopicConfig.SPEED_TEST_TOPIC;

@RestController
@RequestMapping("/api/v1/speed-test")
public class SpeedTestController {
    private final SpeedTestProducer speedTestProducer;

    @Autowired
    public SpeedTestController(SpeedTestProducer speedTestProducer) {
        this.speedTestProducer = speedTestProducer;
    }

    @PostMapping
    public ResponseEntity<KafkaResponse> publishSpeedTest(@RequestBody SpeedTestResult speedTestResult) {
        try {
            speedTestProducer.publish(speedTestResult);
            return new ResponseEntity<>(
                    new KafkaResponse(KafkaConstants.PUBLISHED_STATUS, SPEED_TEST_TOPIC),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new KafkaResponse(KafkaConstants.FAILED_STATUS, SPEED_TEST_TOPIC),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
