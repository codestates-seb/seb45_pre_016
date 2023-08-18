package com.codestates.server.answer.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
public class AnswerResponseDto {
    private Long userId;
    private Long answerId;
    private String content;
    private LocalDateTime modified_At;

}
