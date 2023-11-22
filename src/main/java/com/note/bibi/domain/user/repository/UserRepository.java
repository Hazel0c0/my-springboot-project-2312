package com.note.bibi.domain.user.repository;

import com.note.bibi.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}