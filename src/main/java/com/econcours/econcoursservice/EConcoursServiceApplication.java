package com.econcours.econcoursservice;

import com.econcours.econcoursservice.logger.DefaultConsoleLogger;
import com.econcours.econcoursservice.logger.ECLogger;
import com.econcours.econcoursservice.utils.ProjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class EConcoursServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(EConcoursServiceApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return () -> Optional.of(ProjectUtils.username());
	}

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ECLogger gqLogger() {
		return new DefaultConsoleLogger();
	}

}
