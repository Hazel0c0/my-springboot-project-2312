package com.note.bibi.anonymousboard.repository;

import com.note.bibi.anonymousboard.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Post, Long> {

  @Query("SELECT p FROM Post p " +
      "WHERE :keyword IS NULL OR :keyword = '' OR LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
      "ORDER BY p.createdAt DESC")
  List<Post> findTop100ByTitleContainingOrderByCreatedAtDesc(@Param("keyword") String keyword);
}