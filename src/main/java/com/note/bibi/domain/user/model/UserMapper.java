package com.note.bibi.domain.user.model;

import com.note.bibi.domain.user.controller.dto.SignUpRequestDTO;

public class UserMapper {

  public static User toEntity(SignUpRequestDTO signUpRequestDTO) {
    return User.builder()
        .email(signUpRequestDTO.getEmail())
        .password(signUpRequestDTO.getPassword())
        .build();
  }
}
