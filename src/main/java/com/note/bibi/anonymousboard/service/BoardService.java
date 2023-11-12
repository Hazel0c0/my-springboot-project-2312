package com.note.bibi.anonymousboard.service;

import com.note.bibi.anonymousboard.model.Post;
import com.note.bibi.anonymousboard.model.PostMapper;
import com.note.bibi.anonymousboard.model.dto.PostResponseDTO;
import com.note.bibi.anonymousboard.model.dto.PostRequestDTO;
import com.note.bibi.anonymousboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository;

  public PostResponseDTO savePost(final PostRequestDTO requestPost) {

    return new PostResponseDTO(boardRepository.save(PostMapper.toEntity(requestPost)));
  }

  public List<PostResponseDTO> findAll(){
    List<Post> posts = boardRepository.findAll();
    return posts.stream()
            .map(PostResponseDTO::new)
            .collect(Collectors.toList());
  }

  public PostResponseDTO findById(Long postId){
    return new PostResponseDTO(returnPost(postId));
  }

  private Post returnPost(Long postId) {
    return boardRepository.findById(postId)
       .orElseThrow(() -> new NoSuchElementException("Post not found"));
  }

  public PostResponseDTO updatePost(Long postId, PostRequestDTO updatedPost) {
    Post post = returnPost(postId);
    post.setTitle(updatedPost.getTitle());
    post.setContent(updatedPost.getContent());
    return new PostResponseDTO(post);
  }

  public boolean deletePost(Long postId) {
    try {
      boardRepository.deleteById(postId);
      return true;
    } catch (EmptyResultDataAccessException e) {
      return false;
    }
  }
}
