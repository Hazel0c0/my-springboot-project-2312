package com.note.bibi.anonymousboard.service;

import com.note.bibi.anonymousboard.controller.dto.PostResponseDTO;
import com.note.bibi.anonymousboard.model.Post;
import com.note.bibi.anonymousboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository;
  public PostResponseDTO savePost(final Post post) {
    PostResponseDTO postDTO = new PostResponseDTO(boardRepository.save(post));
    return postDTO;
  }
}
