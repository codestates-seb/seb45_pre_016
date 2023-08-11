package com.codestates.server.question.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionPatchDto {
    private Long userId;
    private String title;
    private String content;
}
