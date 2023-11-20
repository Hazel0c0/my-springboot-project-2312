package com.note.bibi.domain.user.model;

import com.note.bibi.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;

  @Builder
  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
