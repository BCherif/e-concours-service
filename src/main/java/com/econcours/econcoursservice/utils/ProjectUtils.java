package com.econcours.econcoursservice.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;


public class ProjectUtils {
    private ProjectUtils() throws IllegalAccessException {
        throw new IllegalAccessException("can not instantiate this class");
    }

    public static String username() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Objects::nonNull)
                .map(Authentication::getName)
                .orElse("anonymous");
    }
}
