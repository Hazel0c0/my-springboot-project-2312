package com.note.bibi.anonymousboard.controller.dto;

import com.note.bibi.anonymousboard.model.Post;
import lombok.Data;
import lombok.ToString;

@Data
public class PostResponseDTO {
  private Long id;
  private String title;
  private String content;

  public PostResponseDTO(Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.content = post.getContent();
  }
}
