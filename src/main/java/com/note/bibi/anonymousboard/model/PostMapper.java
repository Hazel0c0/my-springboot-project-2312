package com.note.bibi.anonymousboard.model;

import com.note.bibi.anonymousboard.model.Post;
import com.note.bibi.anonymousboard.model.dto.PostRequestDTO;
import org.springframework.stereotype.Component;

public class PostMapper {

  public static Post toEntity(PostRequestDTO postRequestDTO) {
    return Post.builder()
        .title(postRequestDTO.getTitle())
        .content(postRequestDTO.getContent())
        .build();
  }
}
