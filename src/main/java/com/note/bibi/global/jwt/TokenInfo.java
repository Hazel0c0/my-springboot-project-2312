package com.note.bibi.global.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.jwt")
public class TokenInfo {
    private String issuer;  // 발행인
    private String secretKey;

    public Key getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey.getBytes(StandardCharsets.UTF_8));
        System.out.println("decodedKey = " + Arrays.toString(decodedKey));

        return new SecretKeySpec(decodedKey, SignatureAlgorithm.HS256.getJcaName());
    }
}
