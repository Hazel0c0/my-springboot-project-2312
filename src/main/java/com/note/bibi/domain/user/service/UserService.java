package com.note.bibi.domain.user.service;

import com.note.bibi.domain.user.controller.dto.request.LoginRequestDTO;
import com.note.bibi.domain.user.controller.dto.request.SignUpRequestDTO;
import com.note.bibi.domain.user.controller.dto.response.LoginResponseDTO;
import com.note.bibi.domain.user.controller.dto.response.SignUpResponseDTO;
import com.note.bibi.domain.user.model.User;
import com.note.bibi.domain.user.model.UserMapper;
import com.note.bibi.domain.user.repository.UserRepository;
import com.note.bibi.global.jwt.TokenProvider;
import com.note.bibi.global.error.exception.DuplicatedEmailException;
import com.note.bibi.global.error.exception.NoRegisteredArgumentsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
  private final UserRepository userRepository;
  private final TokenProvider tokenProvider;

  // 회원가입
  public SignUpResponseDTO create(SignUpRequestDTO dto) {
    if (dto == null) {
      throw new NoRegisteredArgumentsException("가입 정보가 없습니다.");
    }
    String email = dto.getEmail();

    if (isDuplicate(email)) {
      throw new DuplicatedEmailException("중복된 이메일입니다 : [ " + email + " ]");
    }

    User saved = UserMapper.toEntity(dto);
    userRepository.save(saved);

    log.info(String.valueOf(saved));

    return new SignUpResponseDTO(saved);

  }

  // 이메일 중복 검사
  public boolean isDuplicate(String email) {
    return userRepository.existsByEmail(email);
  }

  // 로그인 - 회원 인증
  public LoginResponseDTO authenticate(LoginRequestDTO dto) {

    // 이메일을 통해 회원 정보 조회
    User user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new RuntimeException("가입된 회원이 아닙니다!"));

    // 패스워드 검증
    String rawPassword = dto.getPassword(); // 입력 비번
    String savedPassword = user.getPassword(); // DB에 저장된 비번

    if (!rawPassword.equals(savedPassword)) {
      throw new RuntimeException("비밀번호가 틀렸습니다.");
    }
    log.info("{}님 로그인 성공!!", user.getEmail());

    // 로그인 성공 후에 클라이언트에 뭘 리턴할 것인가??
    // -> JWT(Access Token)를 클라이언트에게 발급해줘야 함.
    String token = tokenProvider.createToken(user);

    return new LoginResponseDTO(user, token);
  }
}
