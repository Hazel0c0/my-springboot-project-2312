package com.note.bibi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootTest
class BibiApplicationTests {

	@Test
	void SecretKeyGenerator() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] key = new byte[32]; // 이 길이는 시크릿 키의 바이트 길이입니다. 여기서는 32바이트를 사용했습니다.

		secureRandom.nextBytes(key);

		// Base64로 인코딩하여 시크릿 키 생성
		String secretKey = Base64.getEncoder().encodeToString(key);
		System.out.println("Generated Secret Key: " + secretKey);
	}

}
