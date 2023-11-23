package com.note.bibi.domain.user.repository;

import com.note.bibi.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  // 이메일 중복체크
  // @Query("select count(*) from User u where u.email=:email")
  boolean existsByEmail(String email);

  // 이메일로 회원정보 조회
  Optional<User> findByEmail(String email);
}