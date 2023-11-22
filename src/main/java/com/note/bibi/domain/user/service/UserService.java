package com.note.bibi.domain.user.service;

import com.note.bibi.domain.user.controller.dto.LoginRequestDTO;
import com.note.bibi.domain.user.controller.dto.SignUpRequestDTO;
import com.note.bibi.domain.user.model.User;
import com.note.bibi.domain.user.model.UserMapper;
import com.note.bibi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
  private final UserRepository userRepository;

  public void signUpUser(SignUpRequestDTO request) {
    User signedUpUser = UserMapper.toEntity(request);
    userRepository.save(signedUpUser);
    log.info(String.valueOf(signedUpUser));
  }

  public void loginUser(LoginRequestDTO request) {
    userRepository.
  }
}
