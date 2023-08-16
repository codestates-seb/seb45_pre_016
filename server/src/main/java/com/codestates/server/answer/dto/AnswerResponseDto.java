package com.codestates.server.answer.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
public class AnswerResponseDto {
    @Positive
    private Long userId;
    private Long questionId;
    private Long answerId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
