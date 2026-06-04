package com.engrkirky.service_metrics_service.mapper;

import com.engrkirky.service_metrics_service.dto.SpeedTestResultDTO;
import com.engrkirky.service_metrics_service.model.SpeedTestResult;

public class SpeedTestResultMapperImpl implements SpeedTestResultMapper {
    @Override
    public SpeedTestResultDTO convertToDTO(SpeedTestResult speedTestResult) {
        return new SpeedTestResultDTO(
                speedTestResult.getTimestamp(),
                speedTestResult.getIsp(),
                speedTestResult.getIp(),
                speedTestResult.getLocation(),
                speedTestResult.getDownloadSpeedMbps(),
                speedTestResult.getUploadSpeedMbps(),
                speedTestResult.getIdleLatencyMs(),
                speedTestResult.getDownloadLatencyMs(),
                speedTestResult.getUploadLatencyMs()
        );
    }

    @Override
    public SpeedTestResult convertToEntity(SpeedTestResultDTO speedTestResultDTO) {
        return new SpeedTestResult(
                speedTestResultDTO.timestamp(),
                speedTestResultDTO.isp(),
                speedTestResultDTO.ip(),
                speedTestResultDTO.location(),
                speedTestResultDTO.downloadSpeedMbps(),
                speedTestResultDTO.uploadSpeedMbps(),
                speedTestResultDTO.idleLatencyMs(),
                speedTestResultDTO.downloadLatencyMs(),
                speedTestResultDTO.uploadLatencyMs()
        );
    }
}
