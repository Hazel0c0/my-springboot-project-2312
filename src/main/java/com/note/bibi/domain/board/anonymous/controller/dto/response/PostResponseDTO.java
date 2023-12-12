package com.note.bibi.domain.board.anonymous.controller.dto.response;

import com.note.bibi.domain.board.anonymous.model.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
