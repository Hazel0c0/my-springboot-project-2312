package com.note.bibi.domain.board.controller.dto.response;

import com.note.bibi.domain.board.model.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDTO {
  private Long id;
  private String title;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public PostResponseDTO(Post post) {
    this.id = post.getPostId();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.createdAt=post.getCreatedAt();
    this.updatedAt = post.getUpdatedAt();
  }
}
