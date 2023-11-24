package com.note.bibi.domain.user.controller.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {
  @NotBlank(message = "이메일을 입력해주세요")
  @Email(message = "올바른 이메일 형식이어야 합니다")
  private String email;

  @NotBlank(message = "패스워드를 입력해주세요")
  private String password;
}
