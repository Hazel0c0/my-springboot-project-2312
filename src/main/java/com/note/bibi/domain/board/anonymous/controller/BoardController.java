package com.note.bibi.domain.board.anonymous.controller;

import com.note.bibi.domain.board.anonymous.controller.dto.request.PostRequestDTO;
import com.note.bibi.domain.board.anonymous.controller.dto.request.SearchDTO;
import com.note.bibi.domain.board.anonymous.controller.dto.response.PostResponseDTO;
import com.note.bibi.domain.board.anonymous.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/anonymous")
@Slf4j
public class BoardController {
  private final BoardService boardService;

  // 게시글 작성
  @PostMapping
  public ResponseEntity<PostResponseDTO> createPost(@Validated @RequestBody final PostRequestDTO requestPost) {
    PostResponseDTO postDto = boardService.create(requestPost);
    return ResponseEntity.ok(postDto);
  }

  // 게시글 전체 조회
  @GetMapping
  public ResponseEntity<List<PostResponseDTO>> retrievePostList(@Validated @RequestParam(required = false) SearchDTO searchDTO) {
    List<PostResponseDTO> allPostDto = boardService.retrieve(searchDTO);
    return ResponseEntity.ok(allPostDto);
  }

  // 게시글 조회 - id
  @GetMapping("/{postId}")
  public ResponseEntity<PostResponseDTO> retrievePostById(@PathVariable final Long postId) {
    log.info("find post by id - controller : id = " + postId);

      PostResponseDTO postDto = boardService.retrieveById(postId);
      return ResponseEntity.ok(postDto);
  }

  // 게시글 수정
  @PutMapping("/{postId}")
  public ResponseEntity<PostResponseDTO> updatePost(
      @PathVariable final Long postId,
      @Validated @RequestBody final PostRequestDTO updatedPost) {
    log.info("update post by id - controller : " + postId);
    log.info("update post content  :  " + updatedPost.toString());

    PostResponseDTO postDto = boardService.update(postId, updatedPost);
    log.info("update post updated at :  " + postDto.getUpdatedAt());

    return ResponseEntity.ok(postDto);
  }

  // 삭제
  @DeleteMapping("/{postId}")
  public ResponseEntity<Boolean> deletePost(@PathVariable final Long postId) {
    boolean isDeleted = boardService.delete(postId);
    return ResponseEntity.ok(isDeleted);
  }
}
