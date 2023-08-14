package com.codestates.server.user.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * 로그인 시 필요한 데이터
 */
@Getter
@Setter
public class UserLoginDto {
    private long userId;

    private String email;

    private String password;
}
