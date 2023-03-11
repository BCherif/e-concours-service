package com.econcours.econcoursservice.logger;

import com.econcours.econcoursservice.logger.entity.Log;
import com.econcours.econcoursservice.logger.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class DefaultECLogger implements ECLogger {
    private LogRepository logRepository;

    public DefaultECLogger(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void log(LogPayload payload) {
        if (Objects.isNull(payload)) {
            log.info("payload is null");
            return;
        }
        Log l = payload.toLog();
        if (Objects.isNull(l.getUser())) {
            log.info("log user is not informed");
            return;
        }
        if (Objects.isNull(l.getMessage())) {
            log.info("log message is not informed");
            return;
        }
        if (Objects.isNull(l.getLogType())) {
            log.info("log type is not informed");
            return;
        }
        logRepository.save(l);
    }
}
