package com.codestates.server.question.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class QuestionPostDto {
    private Long userId;
    private String title;
    private String content;
    private int views;
}
