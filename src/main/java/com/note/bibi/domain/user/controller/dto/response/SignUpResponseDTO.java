package com.note.bibi.domain.user.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.note.bibi.domain.user.model.User;
import lombok.*;

import java.time.LocalDateTime;


// 회원가입 완료후 클라이언트게 응답할 데이터를 담는 객체
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "email")
public class SignUpResponseDTO {

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinDate;

    public SignUpResponseDTO(User user) {
        this.email = user.getEmail();
        this.joinDate = user.getCreatedAt();
    }
}
