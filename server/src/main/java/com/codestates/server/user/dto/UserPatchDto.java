package com.codestates.server.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserPatchDto {

    private Long userId;

    @NotBlank(message = "회원 이름은 필수값입니다.")
    private String userName;

}
