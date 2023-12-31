package com.note.bibi.domain.user.controller;

import com.note.bibi.domain.user.controller.dto.request.LoginRequestDTO;
import com.note.bibi.domain.user.controller.dto.request.SignUpRequestDTO;
import com.note.bibi.domain.user.controller.dto.response.LoginResponseDTO;
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

  /**
   * 회원가입
   * @param dto : 이메일, 패스워드
   * @return : 이메일, 가입시간
   */
  @PostMapping("/sign-up")
  public ResponseEntity<SignUpResponseDTO> signUp(
      @Validated @RequestBody SignUpRequestDTO dto
  ) {
    log.info("/api/user/sign-up POST! - {}", dto);

    SignUpResponseDTO signUpResponseDTO = userService.create(dto);
    return ResponseEntity.ok().body(signUpResponseDTO);
  }

  /**
   * 로그인
   * @param dto 이메일, 패스워드
   * @return
   */
  @PostMapping("/sign-in")
  public ResponseEntity<LoginResponseDTO> signIn(@Validated @RequestBody LoginRequestDTO dto) {
    LoginResponseDTO responseDTO = userService.authenticate(dto);

    return ResponseEntity.ok().body(responseDTO);
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
}
