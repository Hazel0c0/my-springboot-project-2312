package com.note.bibi.domain.user.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.note.bibi.domain.user.model.User;
import lombok.*;

import java.time.LocalDate;


// 로그인 성공 후 클라이언트에게 전송할 데이터객체
@Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

    private String email;

    @JsonFormat(pattern = "yyyy년 MM월 dd일")
    private LocalDate joinDate;

    private String token; // 인증 토큰
    public LoginResponseDTO(User user, String token) {
        this.email = user.getEmail();
        this.joinDate = LocalDate.from(user.getCreatedAt());
        this.token = token;
    }
}
