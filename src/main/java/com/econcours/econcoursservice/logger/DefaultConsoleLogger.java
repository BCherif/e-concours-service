package com.econcours.econcoursservice.logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultConsoleLogger implements ECLogger {
    @Override
    public void log(LogPayload payload) {
        log.info("{} ({}): {}", payload.getType(), payload.getUser(), payload.getMessage());
    }
}
