package com.econcours.econcoursservice.auth.controller;

import com.econcours.econcoursservice.auth.request.Credentials;
import com.econcours.econcoursservice.base.response.ECResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

    @PostMapping(value = "/login")
    public ECResponse authenticate(@RequestBody Credentials credentials) {
        log.info("credentials: {}", credentials);
        return ECResponse.error("AuthController");
    }
}
