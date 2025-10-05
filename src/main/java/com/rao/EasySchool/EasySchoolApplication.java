package com.rao.EasySchool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.rao.EasySchool.repository")
@EntityScan("com.rao.EasySchool.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class EasySchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasySchoolApplication.class, args);
	}

}
