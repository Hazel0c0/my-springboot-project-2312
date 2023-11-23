package com.note.bibi.domain.user.controller;

import com.note.bibi.domain.user.controller.dto.LoginRequestDTO;
import com.note.bibi.domain.user.controller.dto.SignUpRequestDTO;
import com.note.bibi.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/user")
public class UserController {
  private final UserService userService;

  @PostMapping("/sign-up")
  public ResponseEntity<String> signUp(
      @Validated @RequestPart("user") SignUpRequestDTO request
      , @RequestPart(value = "profileImage", required = false) MultipartFile profileImg
  ) {
    userService.signUpUser(request);
    return ResponseEntity.ok("회원가입 성공");
  }

  @PostMapping("/sign-in")
  public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO request) {
    userService.loginUser(request);
    return ResponseEntity.ok("로그인 성공");
  }
}
