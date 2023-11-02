package com.note.bibi.anonymousboard.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "content", nullable = false)
  private String content;
}
