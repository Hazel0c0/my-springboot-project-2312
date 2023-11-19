package com.note.bibi.domain.board.service;

import com.note.bibi.domain.board.model.Post;
import com.note.bibi.domain.board.model.PostMapper;
import com.note.bibi.domain.board.controller.dto.PostResponseDTO;
import com.note.bibi.domain.board.controller.dto.PostRequestDTO;
import com.note.bibi.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
  private final BoardRepository boardRepository;

  public PostResponseDTO savePost(final PostRequestDTO requestPost) {

    return new PostResponseDTO(boardRepository.save(PostMapper.toEntity(requestPost)));
  }

  public List<PostResponseDTO> findAll(SearchDTO searchDTO){
    List<Post> posts = boardRepository.findRecentPosts(searchDTO.getKeyword);
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
    boardRepository.flush();
    log.info(post.getUpdatedAt()+"-- 수정된 시간");
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
