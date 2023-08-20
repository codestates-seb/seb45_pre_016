package com.codestates.server.user.dto;

import com.codestates.server.user.entity.UserAnswerInfo;
import com.codestates.server.user.entity.UserQuestionInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private Long userId;

    private String userName;

    // ✅ 여기다가 이미지를 넣어서 response..?
    private String image = "https://picsum.photos/200";

    private List<UserQuestionInfo> questions;

    private List<UserAnswerInfo> answers;


}
