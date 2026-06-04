package com.engrkirky.service_metrics_service.mapper;

import com.engrkirky.service_metrics_service.dto.SpeedTestResultDTO;
import com.engrkirky.service_metrics_service.model.SpeedTestResult;

public interface SpeedTestResultMapper {
    SpeedTestResultDTO convertToDTO(SpeedTestResult speedTestResult);
    SpeedTestResult convertToEntity(SpeedTestResultDTO speedTestResultDTO);
}
