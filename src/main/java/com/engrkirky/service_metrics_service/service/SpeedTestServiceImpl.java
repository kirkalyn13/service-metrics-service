package com.engrkirky.service_metrics_service.service;

import com.engrkirky.service_metrics_service.dto.SpeedTestDTO;
import com.engrkirky.service_metrics_service.mapper.SpeedTestMapper;
import com.engrkirky.service_metrics_service.model.SpeedTest;
import com.engrkirky.service_metrics_service.repository.SpeedTestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeedTestServiceImpl implements SpeedTestService {
    private static final Logger log = LoggerFactory.getLogger(SpeedTestServiceImpl.class);
    private final SpeedTestRepository speedTestRepository;
    private final SpeedTestMapper speedTestMapper;

    @Autowired
    public SpeedTestServiceImpl(SpeedTestRepository speedTestRepository, SpeedTestMapper speedTestMapper) {
        this.speedTestRepository = speedTestRepository;
        this.speedTestMapper = speedTestMapper;
    }

    @Override
    public boolean addSpeedTest(SpeedTestDTO speedTestDTO) {
        try {
            SpeedTest speedTest = speedTestMapper.convertToEntity(speedTestDTO);
            log.info("Adding speedTest {}", speedTest);
            speedTestRepository.save(speedTest);

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
