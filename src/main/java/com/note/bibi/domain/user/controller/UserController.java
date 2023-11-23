package com.note.bibi.domain.user.controller;

import com.note.bibi.domain.user.controller.dto.request.SignUpRequestDTO;
import com.note.bibi.domain.user.controller.dto.response.SignUpResponseDTO;
import com.note.bibi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/user")
public class UserController {
  private final UserService userService;

  @PostMapping("/sign-up")
  public ResponseEntity<SignUpResponseDTO> signUp(
      @Validated @RequestBody SignUpRequestDTO dto
  ) {
    log.info("/api/user/sign-up POST! - {}", dto);

    SignUpResponseDTO signUpResponseDTO = userService.create(dto);
    return ResponseEntity.ok().body(signUpResponseDTO);
  }

  // 이메일 중복확인 요청 처리
  // GET: /api/user/check?email=zzzz@xxx.com
  @GetMapping("/check")
  public ResponseEntity<?> check(String email) {
    if (email.trim().equals("")) {
      return ResponseEntity.badRequest()
          .body("이메일이 없습니다!");
    }
    boolean resultFlag = userService.isDuplicate(email);
    log.info("{} 중복?? - {}", email, resultFlag);

    return ResponseEntity.ok().body(resultFlag);
  }

//  @PostMapping("/sign-in")
//  public ResponseEntity<String> signIn(@Validated @RequestBody LoginRequestDTO request) {
//    userService.loginUser(request);
//    return ResponseEntity.ok("로그인 성공");
//  }
}
