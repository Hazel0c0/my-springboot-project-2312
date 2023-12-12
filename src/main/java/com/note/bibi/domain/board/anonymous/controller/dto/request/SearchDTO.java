package com.note.bibi.domain.board.anonymous.controller.dto.request;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class SearchDTO {
  @Size(min = 1, message = "검색 키워드는 공백을 제외한 1글자 이상이어야 합니다.")
  private String keyword;

  public SearchDTO(String keyword) {
    this.keyword = keyword.trim();
  }
}
