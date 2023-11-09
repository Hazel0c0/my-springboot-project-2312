package com.note.bibi.anonymousboard.controller;

import com.note.bibi.anonymousboard.model.Post;
import com.note.bibi.anonymousboard.model.dto.PostDTO;
import com.note.bibi.anonymousboard.model.dto.PostUpdateDTO;
import com.note.bibi.anonymousboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/anonymous")
@Slf4j
public class BoardController {
  private final BoardService boardService;

  // 게시글 작성
  @PostMapping
  public ResponseEntity<PostDTO> createPost(@RequestBody final Post requestPost) {
    PostDTO postDto = boardService.savePost(requestPost);
    return ResponseEntity.ok(postDto);
  }

  // 게시글 전체 조회
  @GetMapping
  public ResponseEntity<List<PostDTO>> getPostAll(){
    List<PostDTO> allPostDto = boardService.findAll();
    return ResponseEntity.ok(allPostDto);
  }

  // 게시글 조회 - id
  @GetMapping(params = "postId")
  public ResponseEntity<PostDTO> getPostsById(@RequestParam final Long postId){
    log.info("find post by id - controller : id = "+postId);

    PostDTO postDto = boardService.findById(postId);
    return ResponseEntity.ok(postDto);
  }

  // 게시글 수정
  @PutMapping("/{postId}")
  public ResponseEntity<PostDTO> updatePost(
      @PathVariable final Long postId,
      @RequestBody final PostUpdateDTO updatedPost) {
    log.info("update post by id - controller : id = "+postId);
    log.info("update post content - controller : content = "+updatedPost.toString());

    PostDTO postDto = boardService.updatePost(postId, updatedPost);
    return ResponseEntity.ok(postDto);
  }

  // 삭제
  @DeleteMapping("/{postId}")
  public ResponseEntity<Boolean> deletePost(@PathVariable final Long postId) {
    boolean isDeleted = boardService.deletePost(postId);
    return ResponseEntity.ok(isDeleted);
  }
}
