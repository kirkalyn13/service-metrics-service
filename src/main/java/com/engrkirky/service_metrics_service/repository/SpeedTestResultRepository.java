package com.engrkirky.service_metrics_service.repository;

import com.engrkirky.service_metrics_service.model.SpeedTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeedTestResultRepository extends JpaRepository<SpeedTestResult, Long> {
}
