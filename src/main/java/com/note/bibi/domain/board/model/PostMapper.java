package com.note.bibi.domain.board.model;

import com.note.bibi.domain.board.controller.dto.request.PostRequestDTO;

public class PostMapper {

  public static Post toEntity(PostRequestDTO postRequestDTO) {
    return Post.builder()
        .title(postRequestDTO.getTitle())
        .content(postRequestDTO.getContent())
        .build();
  }
}
