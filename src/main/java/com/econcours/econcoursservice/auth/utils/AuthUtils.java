package com.econcours.econcoursservice.auth.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthUtils {
    private static final String APPLICATION_JSON = "application/json";

    private AuthUtils() throws IllegalAccessException {
        throw new IllegalAccessException("can not instantiate this class");
    }

    public static Cookie cookie(String name, String value) {
        return new Cookie(name, value);
    }

    public static void sendResponse(HttpServletResponse response, Object data, String contentType, int status) throws IOException {
        response.setContentType(contentType);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.getWriter().print(data);
        response.getWriter().flush();
    }

    public static void sendJsonResponse(HttpServletResponse response, Object data, int status) throws IOException {
        sendResponse(response, data, APPLICATION_JSON, status);
    }

    public static void sendJsonResponse(HttpServletResponse response, Object data) throws IOException {
        sendResponse(response, data, APPLICATION_JSON, HttpServletResponse.SC_OK);
    }

    public static void forbidden(HttpServletResponse response, Object data) throws IOException {
        sendJsonResponse(response, data, HttpServletResponse.SC_FORBIDDEN);
    }
}
