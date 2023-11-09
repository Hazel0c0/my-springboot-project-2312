package com.note.bibi.anonymousboard.service;

import com.note.bibi.anonymousboard.model.dto.PostResponseDTO;
import com.note.bibi.anonymousboard.model.Post;
import com.note.bibi.anonymousboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
  private final BoardRepository boardRepository;

  public PostResponseDTO savePost(final Post post) {
    return new PostResponseDTO(boardRepository.save(post));
  }

  public List<PostResponseDTO> findAllPost(){
    List<Post> posts = boardRepository.findAll();
    return posts.stream()
            .map(PostResponseDTO::new)
            .collect(Collectors.toList());
  }

  public PostResponseDTO findPostById(Long postId){
    log.info("board find post by id - service : id = "+postId);

    return boardRepository.findById(postId)
        .map(PostResponseDTO::new)
        .orElseThrow(() -> new NoSuchElementException("Post not found"));
  }
}
