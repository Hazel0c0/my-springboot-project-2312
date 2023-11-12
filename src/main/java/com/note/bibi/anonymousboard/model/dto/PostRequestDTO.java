package com.note.bibi.anonymousboard.model.dto;

import com.note.bibi.anonymousboard.model.Post;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostRequestDTO {
  private String title;
  private String content;
}
