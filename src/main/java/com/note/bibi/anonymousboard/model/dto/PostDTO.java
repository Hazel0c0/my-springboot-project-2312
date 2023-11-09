package com.note.bibi.anonymousboard.model.dto;

import com.note.bibi.anonymousboard.model.Post;
import lombok.Data;

@Data
public class PostDTO {
  private Long id;
  private String title;
  private String content;

  public PostDTO(Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.content = post.getContent();
  }
}
