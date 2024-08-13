package com.sample.genzschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.sample.genzschool.repository")
@EntityScan("com.sample.genzschool.Model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class GenzschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenzschoolApplication.class, args);
	}

}
