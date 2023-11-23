package com.note.bibi.domain.user.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequestDTO {
  @NotBlank(message = "이메일을 입력해주세요")
  @Email(message = "올바른 이메일 형식이어야 합니다")
  private String email;

  @NotBlank(message = "패스워드를 입력해주세요")
  @Size(min = 8, max = 15, message = "패스워드는 8자 이상 15자 이하여야 합니다")
  private String password;
}
