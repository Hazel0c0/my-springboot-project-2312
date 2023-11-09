package com.note.bibi.anonymousboard.service;

import com.note.bibi.anonymousboard.model.Post;
import com.note.bibi.anonymousboard.model.dto.PostDTO;
import com.note.bibi.anonymousboard.model.dto.PostUpdateDTO;
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

  public PostDTO savePost(final Post post) {
    return new PostDTO(boardRepository.save(post));
  }

  public List<PostDTO> findAll(){
    List<Post> posts = boardRepository.findAll();
    return posts.stream()
            .map(PostDTO::new)
            .collect(Collectors.toList());
  }

  public PostDTO findById(Long postId){
    return new PostDTO(returnPost(postId));
  }

  private Post returnPost(Long postId) {
    return boardRepository.findById(postId)
       .orElseThrow(() -> new NoSuchElementException("Post not found"));
  }

  public PostDTO updatePost(Long postId, PostUpdateDTO updatedPost) {
    Post post = returnPost(postId);
    post.setTitle(updatedPost.getTitle());
    post.setContent(updatedPost.getContent());
    return new PostDTO(post);
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
