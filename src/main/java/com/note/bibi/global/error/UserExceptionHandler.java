package com.note.bibi.global.error;

import com.note.bibi.global.error.exception.DuplicatedEmailException;
import com.note.bibi.global.error.exception.NoRegisteredArgumentsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {
  @ExceptionHandler(NoRegisteredArgumentsException.class)
  public ResponseEntity<?> handleNoRegisteredArgumentsException(NoRegisteredArgumentsException e) {
    log.warn("필수 가입 정보를 전달받지 못했습니다.");
    return ResponseEntity.badRequest()
        .body(e.getMessage());
  }

  @ExceptionHandler(DuplicatedEmailException.class)
  public ResponseEntity<?> handleDuplicatedEmailException(DuplicatedEmailException e) {
    log.warn("이메일 중복입니다!");
    return ResponseEntity.badRequest()
        .body(e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleOtherExceptions(Exception e) {
    log.warn("기타 예외가 발생했습니다. - {}", e.getMessage());
//    e.printStackTrace();
//    return ResponseEntity.internalServerError().build();
    return ResponseEntity.badRequest().body(e.getMessage());
  }
}

