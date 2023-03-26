package com.econcours.econcoursservice.auth.provider.jwt;

import com.econcours.econcoursservice.auth.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@EnableWebSecurity
@Slf4j
@Configuration
public class JwtWebSecurity extends WebSecurityConfigurerAdapter {

    private JwtUserDetailsServiceImpl jwtUserDetailsService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JwtAutProperties properties;
    private ObjectMapper mapper;

    public JwtWebSecurity(JwtUserDetailsServiceImpl jwtUserDetailsService,
                          PasswordEncoder passwordEncoder,
                          UserRepository userRepository,
                          JwtAutProperties properties,
                          ObjectMapper mapper) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.properties = properties;
        this.mapper = mapper;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, JwtConstants.SIGN_UP_URL)
                .permitAll()
                //.antMatchers("/images/**", JwtConstants.COMPETITION_URL, JwtConstants.ESTABLISHMENT_URL)
                .antMatchers(JwtConstants.COMPETITION_URL, JwtConstants.ESTABLISHMENT_URL, JwtConstants.MAIL_URL, JwtConstants.CANDIDATE_URL)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JwtAuthFilter(authenticationManager(), userRepository, properties, mapper))
                .addFilter(new JwtAuthzFilter(authenticationManager(), userRepository, properties));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowedHeaders(getAllowedHeaders());
        corsConfiguration.setAllowedMethods(getAllowedMethods());
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    private List<String> getAllowedHeaders() {
        return Arrays.asList("origin",
                "content-type",
                "accept",
                "enctype",
                "authorization",
                "x-req",
                "Origin",
                "Content-Type",
                "Accept",
                "Enctype",
                "Authorization",
                "X-req");
    }

    private List<String> getAllowedMethods() {
        return Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
