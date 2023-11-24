package com.note.bibi;

import com.note.bibi.global.jwt.TokenInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@EnableConfigurationProperties(TokenInfo.class)
@SpringBootApplication
public class BibiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibiApplication.class, args);
	}

}
