package com.note.bibi.anonymousboard.model.dto;

import com.note.bibi.anonymousboard.model.Post;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDTO {
  private Long id;
  private String title;
  private String content;
  private LocalDateTime createdAt;

  public PostResponseDTO(Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.content = post.getContent();
  }


}
