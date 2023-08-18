package com.codestates.server.user.entity;

import com.codestates.server.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserQuestionInfo {
    private Long questionId;
    private String title;
//    private String content;
    private LocalDateTime created_At;

}
