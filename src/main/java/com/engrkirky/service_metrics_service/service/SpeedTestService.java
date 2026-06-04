package com.engrkirky.service_metrics_service.service;

import com.engrkirky.service_metrics_service.dto.SpeedTestDTO;

/**
 * Service for managing speed test data.
 */
public interface SpeedTestService {
    void addSpeedTest(SpeedTestDTO speedTestDTO);
}
