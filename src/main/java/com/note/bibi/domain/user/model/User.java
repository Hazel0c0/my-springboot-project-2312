package com.note.bibi.domain.user.model;

import com.note.bibi.global.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity {
  @Id
  @Column(name = "user_id")
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id; // 계정명이 아니라 식별코드

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
