package com.codestates.server.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class AnswerPatchDto {
    private Long answerId;

    @Positive
    private Long userId;
    @NotBlank
    private String content;
}
