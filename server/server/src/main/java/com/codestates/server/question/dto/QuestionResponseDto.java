package com.codestates.server.question.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionResponseDto {

    private Long questionId;

    private String title;

    private String content;

    private Long views;

    private LocalDateTime created_At;

    private LocalDateTime modified_At;
}
