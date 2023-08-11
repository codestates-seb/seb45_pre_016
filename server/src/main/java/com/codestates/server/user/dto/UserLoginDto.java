package com.codestates.server.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {

    private long memberId;

    private String email;

    private String password;
}
