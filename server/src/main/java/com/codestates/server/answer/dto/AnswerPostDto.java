package com.codestates.server.answer.dto;

import com.codestates.server.question.entity.Question;
import com.codestates.server.user.entity.User;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class AnswerPostDto {

    @Positive
    private Long userId;
    @NotBlank(message = "답변 내용을 적어주세요")
    private String content;

    public AnswerPostDto(Long userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}
