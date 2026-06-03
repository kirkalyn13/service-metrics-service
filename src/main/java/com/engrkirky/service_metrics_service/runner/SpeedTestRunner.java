package com.engrkirky.service_metrics_service.runner;

import com.engrkirky.service_metrics_service.dto.SpeedTestResult;
import com.engrkirky.service_metrics_service.producer.SpeedTestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpeedTestRunner {
    private final SpeedTestProducer speedTestProducer;

    @Autowired
    public SpeedTestRunner(SpeedTestProducer speedTestProducer) {
        this.speedTestProducer = speedTestProducer;
    }

    @Bean
    ApplicationRunner runner() {
        return args -> {
            speedTestProducer.publish(new SpeedTestResult(
                    "2026-05-25T09:24:36.148779Z",
                    "Spectrum", "192.168.0.10", "Nashville, TN",
                    1037.11, 39.28, 28.0, 34.0, 27.0
            ));
        };
    }
}
