package com.econcours.econcoursservice.logger;

import com.econcours.econcoursservice.logger.entity.Log;
import com.econcours.econcoursservice.logger.enumeration.LogType;
import com.econcours.econcoursservice.utils.ProjectUtils;
import lombok.*;

@Data
@Builder
public class LogPayload {
    private String user;
    private String message;
    private LogType type;

    public static LogPayload from(LogType type, String message) {
        return LogPayload
                .builder()
                .user(ProjectUtils.username())
                .type(type)
                .message(message)
                .build();
    }

    public Log toLog() {
        return Log
                .builder()
                .user(user)
                .logType(type)
                .message(message)
                .build();
    }
}
