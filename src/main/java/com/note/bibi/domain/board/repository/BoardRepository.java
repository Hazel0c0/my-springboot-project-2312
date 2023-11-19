package com.note.bibi.domain.board.repository;

import com.note.bibi.domain.board.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Post, Long> {

  @Query("SELECT p FROM Post p " +
      "WHERE :keyword IS NULL OR :keyword = '' OR LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
      "ORDER BY p.createdAt DESC")
  List<Post> findRecentPosts(@Param("keyword") String keyword);
}