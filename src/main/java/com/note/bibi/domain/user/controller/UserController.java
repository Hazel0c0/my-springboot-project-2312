package com.note.bibi.domain.user.controller;

import com.note.bibi.domain.user.controller.dto.LoginRequestDTO;
import com.note.bibi.domain.user.controller.dto.SignUpRequestDTO;
import com.note.bibi.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequestDTO request) {
    userService.signUpUser(request);
    return ResponseEntity.ok("회원가입 성공");
  }

  @PostMapping
  public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO request) {
    userService.loginUser(request);
    return ResponseEntity.ok("로그인 성공");
  }
}
