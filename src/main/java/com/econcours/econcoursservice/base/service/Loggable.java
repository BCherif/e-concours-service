package com.econcours.econcoursservice.base.service;


import com.econcours.econcoursservice.logger.enumeration.LogType;

import java.util.function.Predicate;

public interface Loggable<T> {
    void log(LogType type, String message);
    String createMessage();
    String updateMessage();
    String deleteMessage();

    default String persistMessage(Predicate<T> predicate, T value) {
        return predicate.test(value) ? createMessage() : updateMessage();
    }
}
