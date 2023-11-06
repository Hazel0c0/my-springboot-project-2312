package com.note.bibi.anonymousboard.repository;

import com.note.bibi.anonymousboard.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Post, Long> {
}