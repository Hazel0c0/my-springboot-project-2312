package com.note.bibi.anonymousboard.controller;

import com.note.bibi.anonymousboard.model.dto.PostResponseDTO;
import com.note.bibi.anonymousboard.model.Post;
import com.note.bibi.anonymousboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/anonymous")
public class BoardController {
  private final BoardService boardService;

  // 게시글 작성
  @PostMapping
  public ResponseEntity<PostResponseDTO> createPost(@RequestBody final Post requestPost) {
    PostResponseDTO postDto = boardService.savePost(requestPost);
    return ResponseEntity.ok(postDto);
  }

  // 게시글 전체 조회
  @GetMapping
  public ResponseEntity<List<PostResponseDTO>> getPostAll(){
    List<PostResponseDTO> allPostDto = boardService.findAllPost();
    return ResponseEntity.ok(allPostDto);
  }

  // 게시글 전체 조회
  @GetMapping
  public ResponseEntity<List<PostResponseDTO>> getPosts(){
    List<PostResponseDTO> allPostDto = boardService.findAllPost();
    return ResponseEntity.ok(allPostDto);
  }
}
