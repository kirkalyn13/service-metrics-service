package com.engrkirky.service_metrics_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST controller for health check operations.
 */
@RestController
@RequestMapping("/api/v1/health")
public class HeatlhController {
    /**
     * Returns the application health status.
     *
     * @return health check response
     */
    @GetMapping
    public ResponseEntity<HealthCheckResponse> getHealthCheck() {
        return new ResponseEntity<>(new HealthCheckResponse("ok"), HttpStatus.OK);
    }

    /**
     * Health check response payload.
     *
     * @param status application status
     */
    private record HealthCheckResponse(String status) {}
}
