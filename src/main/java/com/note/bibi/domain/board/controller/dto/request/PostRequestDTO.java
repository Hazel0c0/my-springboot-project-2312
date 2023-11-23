package com.note.bibi.domain.board.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostRequestDTO {
  @NotBlank(message = "제목을 입력해주세요")
  @Size(min = 1, max = 30, message = "제목은 1글자 이상 30글자 이하여야 합니다")
  private String title;

  @NotBlank(message = "내용을 입력해주세요")
  @Size(min = 1, max = 1000, message = "내용은 1글자 이상 1000글자 이하여야 합니다")
  private String content;
}
