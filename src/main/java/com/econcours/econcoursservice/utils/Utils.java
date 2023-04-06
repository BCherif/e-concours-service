package com.econcours.econcoursservice.utils;

import com.econcours.econcoursservice.base.response.ECResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class Utils {
    private Utils() throws IllegalAccessException {
        throw new IllegalAccessException("can not instantiate this class");
    }

    public static <T> List<T> emptyList(int initialSize) {
        return new ArrayList<>(initialSize);
    }

    public static Date dateBasedOnCurrentTime(long timestampsToAdd) {
        return new Date(System.currentTimeMillis() + timestampsToAdd);
    }

    public static Date date(long timestamps) {
        return new Date(timestamps);
    }

    public static Date now() {
        return date(System.currentTimeMillis());
    }

    public static <T> List<T> emptyList() {
        return emptyList(0);
    }

    public static String uid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static <T> Map.Entry<String, T> dict(String key, T value) {
        return new AbstractMap.SimpleEntry(key, value);
    }

    public static <T> ResponseEntity<ECResponse<T>> created(ECResponse<T> entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    public static boolean isGreaterThanZero(Long value) {
        return Objects.nonNull(value) && value > 0;
    }

    public static boolean isCorrectUid(String uid) {
        return Objects.nonNull(uid) && uid.length() == 32;
    }

    public static <E> List<E> removeDuplicates(List<E> list) {
        List<E> newList = new ArrayList<>();
        for (E element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }
}
