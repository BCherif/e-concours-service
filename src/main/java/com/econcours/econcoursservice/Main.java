package com.econcours.econcoursservice;

import com.econcours.econcoursservice.auth.entity.User;
import com.econcours.econcoursservice.auth.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class Main implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public Main(PasswordEncoder passwordEncoder,
                UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userService.getRepository().findByUsername("kmaiga") != null) return;
        User user = User
                .builder()
                .username("kmaiga")
                .password(passwordEncoder.encode("kmaiga"))
                .active(true)
                .admin(true)
                .build();
        userService.save(user);
    }
}
