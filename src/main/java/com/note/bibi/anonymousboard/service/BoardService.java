package com.note.bibi.anonymousboard.service;

import com.note.bibi.anonymousboard.model.dto.PostResponseDTO;
import com.note.bibi.anonymousboard.model.Post;
import com.note.bibi.anonymousboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository;

  public PostResponseDTO savePost(final Post post) {
    return new PostResponseDTO(boardRepository.save(post));
  }

  public List<PostResponseDTO> findAllPost(){
    List<Post> posts = boardRepository.findAll();
    return posts.stream()
            .map(post -> new PostResponseDTO(post))
            .collect(Collectors.toList());
  }
}
