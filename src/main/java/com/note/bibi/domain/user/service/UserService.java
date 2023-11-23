package com.note.bibi.domain.user.service;

import com.note.bibi.domain.user.controller.dto.request.SignUpRequestDTO;
import com.note.bibi.domain.user.controller.dto.response.SignUpResponseDTO;
import com.note.bibi.domain.user.model.User;
import com.note.bibi.domain.user.model.UserMapper;
import com.note.bibi.domain.user.repository.UserRepository;
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

  public SignUpResponseDTO create(SignUpRequestDTO request) {
    if (request == null) {
      throw new NoRegisteredArgumentsException("가입 정보가 없습니다.");
    }
    String email = request.getEmail();

    if (isDuplicate(email)) {
      throw new DuplicatedEmailException("중복된 이메일입니다 : [ "+email+" ]");
    }

    User saved = UserMapper.toEntity(request);
    userRepository.save(saved);

    log.info(String.valueOf(saved));

    return new SignUpResponseDTO(saved);

  }

//  public void loginUser(LoginRequestDTO request) {
//    userRepository.
//  }

  public boolean isDuplicate(String email) {
    return userRepository.existsByEmail(email);
  }
}
