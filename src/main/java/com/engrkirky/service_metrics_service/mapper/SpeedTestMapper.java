package com.engrkirky.service_metrics_service.mapper;

import com.engrkirky.service_metrics_service.dto.SpeedTestDTO;
import com.engrkirky.service_metrics_service.model.SpeedTest;

/**
 * Mapper for converting between SpeedTest entities and DTOs.
 */
public interface SpeedTestMapper {
    SpeedTestDTO convertToDTO(SpeedTest speedTest);
    SpeedTest convertToEntity(SpeedTestDTO speedTestDTO);
}
