package com.econcours.econcoursservice.auth.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.econcours.econcoursservice.auth.entity.User;
import com.econcours.econcoursservice.auth.provider.jwt.JwtAutProperties;
import com.econcours.econcoursservice.auth.repository.UserRepository;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import org.springframework.stereotype.Service;

import static com.econcours.econcoursservice.utils.Utils.dateBasedOnCurrentTime;

@Service
public class UserService extends ECDefaultBaseService<User, UserRepository> {
    private final JwtAutProperties properties;
    private final UserRepository userRepository;
    public UserService(UserRepository repository, ECEntityManager manager, ECLogger logger, JwtAutProperties properties, UserRepository userRepository) {
        super(repository, manager, logger);
        this.properties = properties;
        this.userRepository = userRepository;
    }

    public ECResponse<User> changePwd() {
        return ECResponse.success(null);
    }

    public String createToken(String username) {
        User user = userRepository.findByUsername(username);
        return JWT.create()
                .withSubject(username)
                .withIssuer(username)
                .withExpiresAt(dateBasedOnCurrentTime(properties.getExpiration()))
                .withClaim("admin", user.isAdmin())
                .withClaim("active", user.isActive())
                .withClaim("uid", user.getUid())
                .withClaim("username", username)
                .sign(Algorithm.HMAC512(properties.getSecret()));
    }
}
