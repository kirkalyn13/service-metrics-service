package com.engrkirky.service_metrics_service.runner;

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
        return args -> {speedTestProducer.publish("Speed Test Data from Runner.");};
    }
}
