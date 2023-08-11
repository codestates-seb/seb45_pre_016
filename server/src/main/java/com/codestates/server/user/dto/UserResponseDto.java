package com.codestates.server.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private String username;

    private String email;

    private String image;

    private String createdAt;

    private long totalUserQuestions;

    private long totalUserAnswers;

}
