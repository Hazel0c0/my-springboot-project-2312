package com.note.bibi.board.model;

import com.note.bibi.board.controller.dto.PostRequestDTO;

public class PostMapper {

  public static Post toEntity(PostRequestDTO postRequestDTO) {
    return Post.builder()
        .title(postRequestDTO.getTitle())
        .content(postRequestDTO.getContent())
        .build();
  }
}
