package com.note.bibi.global.config.jwt;

import com.note.bibi.domain.user.model.User;
import com.note.bibi.global.config.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

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

    return Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 type : JWT
        .setIssuer(tokenInfo.getIssuer()) // 내용 iss
        .setIssuedAt(new Date()) // 내용 iat : 토큰 발급 시간
        .setExpiration(expiryDate) // 내용 exp : 유효 시간
        .setSubject(user.getEmail()) // 내용 sub : 유저의 이메일
        .claim("ID", user.getId()) // 클레임 id : 유저 ID
        // 서명 : 비밀값과 함께 해시값을 HS256 방식으로 암호화
        .signWith(tokenInfo.getSecretKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  // 토큰 유효성 검사
  public boolean validateToken(String token) {
    try {
      Jwts.parser()
          .setSigningKey(tokenInfo.getSecretKey())
          .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // CustomUserDetails 취득
  public CustomUserDetails getCustomUserDetails(final String token) {
    Assert.hasText(token, "token parameter must not be empty or null");

    return new CustomUserDetails(getUserId(token));
  }

  // 토큰 기반으로 유저 인증 정보 취득
  public UsernamePasswordAuthenticationToken getAuthentication(String token) {
    Claims claims = getClaims(token);
    Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

    return new UsernamePasswordAuthenticationToken(
        new org.springframework.security.core.userdetails.User
            (claims.getSubject(), "", authorities), token, authorities);
  }

  // 토큰 기반으로 유저 아이디 취득
  public Long getUserId(String token) {
    Claims claims = getClaims(token);
    return claims.get("ID", Long.class);
  }

  private Claims getClaims(String token) {
    return Jwts.parser()
        .setSigningKey(tokenInfo.getSecretKey())
        .parseClaimsJws(token)
        .getBody();
  }
}
