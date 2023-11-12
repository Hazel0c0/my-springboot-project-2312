package com.note.bibi.anonymousboard.repository;

import com.note.bibi.anonymousboard.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Post, Long> {

  List<Post> findTop100ByOrderByCreatedAtDesc();

}