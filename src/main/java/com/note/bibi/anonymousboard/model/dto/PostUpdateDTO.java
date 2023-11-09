package com.note.bibi.anonymousboard.model.dto;

import com.note.bibi.anonymousboard.model.Post;
import lombok.Data;

@Data
public class PostUpdateDTO {
  private String title;
  private String content;

}
