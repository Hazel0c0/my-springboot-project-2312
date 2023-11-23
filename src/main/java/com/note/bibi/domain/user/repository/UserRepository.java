package com.note.bibi.domain.user.repository;

import com.note.bibi.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  // 이메일 중복체크
  // @Query("select count(*) from User u where u.email=:email")
  boolean existsByEmail(String email);
}