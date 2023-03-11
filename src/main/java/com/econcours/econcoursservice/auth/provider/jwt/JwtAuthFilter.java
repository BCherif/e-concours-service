package com.econcours.econcoursservice.auth.provider.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.econcours.econcoursservice.auth.exception.AuthAuthenticationException;
import com.econcours.econcoursservice.auth.repository.UserRepository;
import com.econcours.econcoursservice.auth.request.Credentials;
import com.econcours.econcoursservice.auth.utils.AuthUtils;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.*;
import java.io.IOException;

import static com.econcours.econcoursservice.utils.Utils.dateBasedOnCurrentTime;
import static com.econcours.econcoursservice.utils.Utils.emptyList;
import static java.lang.String.format;

@Slf4j
public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {

    private static String UNSUPPORTED_METHOD_MESSAGE = "La méthode d'authentification est non supportée";
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtAutProperties properties;
    private ObjectMapper mapper;

    public JwtAuthFilter(AuthenticationManager authenticationManager,
                         UserRepository userRepository,
                         JwtAutProperties properties,
                         ObjectMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.properties = properties;
        this.mapper = mapper;
    }

    @Override
    public void setFilterProcessesUrl(String filterProcessesUrl) {
        super.setFilterProcessesUrl(filterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) throw new AuthenticationServiceException(format("%s:  %s", UNSUPPORTED_METHOD_MESSAGE, request.getMethod()));
            try {
                ServletInputStream inputStream = request.getInputStream();
                if (inputStream.available() == 0) throw new RuntimeException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials"));
                Credentials credentials = mapper.readValue(inputStream, Credentials.class);
                UsernamePasswordAuthenticationToken at = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword(), emptyList());
                return authenticationManager.authenticate(at);
            } catch (Exception e) {
                throw new AuthAuthenticationException(e.getMessage(), e);
            }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((User) authResult.getPrincipal()).getUsername();
        com.econcours.econcoursservice.auth.entity.User user = userRepository.findByUsername(username);
        String token = JWT.create()
                .withSubject(username)
                .withIssuer(username)
                .withExpiresAt(dateBasedOnCurrentTime(properties.getExpiration()))
                .withClaim("admin", user.isAdmin())
                .withClaim("active", user.isActive())
                .withClaim("uid", user.getUid())
                .withClaim("username", username)
                .sign(Algorithm.HMAC512(properties.getSecret()));
        String data = mapper.writeValueAsString(ECResponse.success(Utils.dict(JwtConstants.TOKEN_KEY, token)));
        AuthUtils.sendJsonResponse(response, data);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        String data = mapper.writeValueAsString(ECResponse.error(failed.getMessage()));
        AuthUtils.forbidden(response, data);
    }
}
