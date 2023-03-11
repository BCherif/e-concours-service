package com.econcours.econcoursservice.auth.provider.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.econcours.econcoursservice.auth.entity.User;
import com.econcours.econcoursservice.auth.exception.AuthException;
import com.econcours.econcoursservice.auth.repository.UserRepository;
import com.econcours.econcoursservice.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class JwtAuthzFilter extends BasicAuthenticationFilter {

    private final JwtAutProperties properties;
    private final UserRepository userRepository;

    public JwtAuthzFilter(AuthenticationManager authenticationManager, UserRepository userRepository, JwtAutProperties properties) {
        super(authenticationManager);
        this.properties = properties;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization, X-Requested-With");

            String method = request.getMethod();
            if ("OPTIONS".equalsIgnoreCase(method)) {
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }

           String token = request.getHeader(JwtConstants.HEADER);

            if (Objects.isNull(token) || !token.startsWith(JwtConstants.TOKEN_PREFIX)) {
                chain.doFilter(request, response);
                return;
            }

            String username = JWT.require(Algorithm.HMAC512(properties.getSecret().getBytes()))
                    .build()
                    .verify(token.replace(JwtConstants.TOKEN_PREFIX, ""))
                    .getSubject();

            if (Objects.isNull(username)) {
                chain.doFilter(request, response);
                return;
            }

            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new RuntimeException("user not found");
            }

            if (!user.isActive()) {
                throw new RuntimeException("user account is deactivate");
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, Utils.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw new AuthException(e);
        }
    }
}
