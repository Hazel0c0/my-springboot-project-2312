package com.note.bibi.domain.board.anonymous.model;

import com.note.bibi.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "postId", callSuper = false)
@Builder

@Entity
@Table(name = "tbl_post")
public class Post extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;
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
