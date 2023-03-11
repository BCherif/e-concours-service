package com.econcours.econcoursservice.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthAuthenticationException extends AuthenticationException {
    public AuthAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthAuthenticationException(String msg) {
        super(msg);
    }
}
