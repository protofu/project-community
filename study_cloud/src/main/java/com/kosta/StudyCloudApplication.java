package com.kosta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudyCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyCloudApplication.class, args);
	}

}
