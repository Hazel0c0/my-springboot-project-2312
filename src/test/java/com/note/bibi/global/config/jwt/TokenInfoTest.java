package com.note.bibi.global.config.jwt;

import com.note.bibi.global.jwt.TokenInfo;
import com.note.bibi.global.jwt.TokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TokenInfoTest {
  @Test
  void testGetKey() {
    TokenInfo tokenInfo = new TokenInfo();
    TokenProvider tokenProvider=new TokenProvider(tokenInfo);
//    User byId = user.findById(1L).orElseThrow();

//    tokenProvider.createToken(byId);
  }
}