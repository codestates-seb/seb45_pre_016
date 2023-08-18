package com.codestates.server.user.dto;

import com.codestates.server.user.entity.UserQuestionInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {

    private String userName;

    // ✅ 여기다가 이미지를 넣어서 response..?
    private String image = "https://picsum.photos/200";

//    ⏹️ 리스트 변경 및 매핑 예정
    private List<UserQuestionInfo> questions;


//    ⏹️ 리스트 변경 및 매핑 예정
//    private List<UserAnswer> userAnswers;


}
