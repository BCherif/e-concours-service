package com.econcours.econcoursservice.auth.provider.jwt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtAutProperties {
    @Value("${econcours.auth.jwt.secret}")
    private String secret;

    @Value("${econcours.auth.jwt.expiration}")
    private long expiration;
}
