package com.note.bibi.anonymousboard.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class) // 부모 엔터티의 리스너를 지정
@NoArgsConstructor
public class Post extends DataTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "content", nullable = false)
  private String content;

  @Builder
  public Post(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
