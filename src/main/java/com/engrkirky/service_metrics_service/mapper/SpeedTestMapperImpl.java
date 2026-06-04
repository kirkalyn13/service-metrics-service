package com.engrkirky.service_metrics_service.mapper;

import com.engrkirky.service_metrics_service.dto.SpeedTestDTO;
import com.engrkirky.service_metrics_service.model.SpeedTest;
import org.springframework.stereotype.Component;

/**
 * Mapper implementation for converting between SpeedTest entities and DTOs.
 */
@Component
public class SpeedTestMapperImpl implements SpeedTestMapper {
    /**
     * Converts a SpeedTest entity to a DTO.
     *
     * @param speedTest source entity
     * @return mapped DTO
     */
    @Override
    public SpeedTestDTO convertToDTO(SpeedTest speedTest) {
        return new SpeedTestDTO(
                speedTest.getTimestamp(),
                speedTest.getIsp(),
                speedTest.getIp(),
                speedTest.getLocation(),
                speedTest.getDownloadSpeedMbps(),
                speedTest.getUploadSpeedMbps(),
                speedTest.getIdleLatencyMs(),
                speedTest.getDownloadLatencyMs(),
                speedTest.getUploadLatencyMs()
        );
    }

    /**
     * Converts a SpeedTest DTO to an entity.
     *
     * @param speedTestDTO source DTO
     * @return mapped entity
     */
    @Override
    public SpeedTest convertToEntity(SpeedTestDTO speedTestDTO) {
        return SpeedTest.builder()
                .timestamp(speedTestDTO.timestamp())
                .isp(speedTestDTO.isp())
                .ip(speedTestDTO.ip())
                .location(speedTestDTO.location())
                .downloadSpeedMbps(speedTestDTO.downloadSpeedMbps())
                .uploadSpeedMbps(speedTestDTO.uploadSpeedMbps())
                .idleLatencyMs(speedTestDTO.idleLatencyMs())
                .downloadLatencyMs(speedTestDTO.downloadLatencyMs())
                .uploadLatencyMs(speedTestDTO.uploadLatencyMs())
                .build();
    }
}
