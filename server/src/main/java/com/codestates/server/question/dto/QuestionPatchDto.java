package com.codestates.server.question.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class QuestionPatchDto {

    private Long questionId;

    @NotBlank
    private Long userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
