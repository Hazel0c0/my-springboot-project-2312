package com.note.bibi.global.config.jwt;

import lombok.*;

@Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenUserInfo {

    private String userId;
    private String email;
}
