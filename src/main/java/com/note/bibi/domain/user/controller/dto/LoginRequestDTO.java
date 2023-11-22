package com.note.bibi.domain.user.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
  @NotBlank(message = "이메일을 입력해주세요")
  @Email(message = "올바른 이메일 형식이어야 합니다")
  private String email;

  @NotBlank(message = "패스워드를 입력해주세요")
  private String password;
}