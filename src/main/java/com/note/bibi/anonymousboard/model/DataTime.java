package com.note.bibi.anonymousboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class DataTime   {
  @CreatedDate
  private LocalDateTime createdAt;

}
