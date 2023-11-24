package com.note.bibi.global.config.jwt;

import com.note.bibi.domain.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenProvider {
  private final TokenInfo tokenInfo;

  /**
   * JWT 토큰 생성 메서드
   *
   * @param user : 토큰 내용에 포함될 로그인 유저 정보
   * @return : 생성된 Json 을 암호화한 토큰 값
   */
  public String createToken(User user) {
    log.info("CREATING TOKEN...");

    // 토큰 만료 시간 : 24H
    Date expiryDate = Date.from(    // `Instant`를 `Date` 로 변환
        Instant.now()   // 현재 시간 정보를 `Instant`로 가져온다
            .plus(1, ChronoUnit.DAYS) // 현재 시각 + 1일
    );
    System.out.println("expiryDate = " + expiryDate);

    // 추가 클레임 정의
    Map<String, Object> claims = new HashMap<>();
    claims.put("email", user.getEmail());

    return Jwts.builder()
        // token header에 들어갈 서명 : 비밀값과 함께 해시값을 HS256 방식으로 암호화
        .signWith(
            tokenInfo.getSecretKey()
            , SignatureAlgorithm.HS256
        )
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 type : JWT
        .setClaims(claims) // 추가클레임은 먼저 설정해야 함
        // 추가하는 클레임이 이미 토큰에 포함된 다른 정보들과 충돌하지 않도록 해야하기 때문
        .setIssuer(tokenInfo.getIssuer()) // 내용 iss
        .setIssuedAt(new Date()) // 내용 iat : 토큰 발급 시간
        .setExpiration(expiryDate) // 내용 exp : 유효 시간
        .setSubject(user.getId()) // sub: 토큰을 식별할 수 있는 주요데이터
        .compact();
  }

  /**
   * 클라이언트가 전송한 토큰을 디코딩하여 토큰의 위조여부를 확인
   * 토큰을 json으로 파싱해서 클레임(토큰정보)를 리턴
   * @param token
   * @return - 토큰 안에있는 인증된 유저정보를 반환
   */
  public TokenUserInfo validateAndGetTokenUserInfo(String token) {

    Claims claims = Jwts.parserBuilder()
        // 토큰 발급자의 발급 당시의 서명을 넣어줌
        .setSigningKey(tokenInfo.getSecretKey())
        // 서명 위조 검사: 위조된경우 예외가 발생합니다.
        // 위조가 되지 않은 경우 페이로드를 리턴
        .build()
        .parseClaimsJws(token)
        .getBody();

    log.info("claims: {}", claims);

    return TokenUserInfo.builder()
        .userId(claims.getSubject())
        .email(claims.get("email", String.class))
        .build();
  }
}
