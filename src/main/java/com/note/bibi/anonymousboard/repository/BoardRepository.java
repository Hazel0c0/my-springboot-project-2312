package com.note.bibi.anonymousboard.repository;

import com.note.bibi.anonymousboard.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Post, Long> {
}